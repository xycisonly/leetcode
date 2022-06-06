//力扣数据中心有 n 台服务器，分别按从 0 到 n-1 的方式进行了编号。它们之间以「服务器到服务器」点对点的形式相互连接组成了一个内部集群，其中连接 co
//nnections 是无向的。从形式上讲，connections[i] = [a, b] 表示服务器 a 和 b 之间形成连接。任何服务器都可以直接或者间接地通
//过网络到达任何其他服务器。 
//
// 「关键连接」 是在该集群中的重要连接，也就是说，假如我们将它移除，便会导致某些服务器无法访问其他服务器。 
//
// 请你以任意顺序返回该集群内的所有 「关键连接」。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
//输出：[[1,3]]
//解释：[[3,1]] 也是正确的。 
//
// 示例 2: 
//
// 
//输入：n = 2, connections = [[0,1]]
//输出：[[0,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 10^5 
// n-1 <= connections.length <= 10^5 
// connections[i][0] != connections[i][1] 
// 不存在重复的连接 
// 
// Related Topics 深度优先搜索 图 双连通分量 
// 👍 193 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P1192CriticalConnectionsInANetwork {
    public static void main(String[] args) {
        Solution solution = new P1192CriticalConnectionsInANetwork().new Solution();
        List<List<Integer>> paramList = new ArrayList<>();
        paramList.add(Arrays.asList(0, 1));
        paramList.add(Arrays.asList(3, 2));
        paramList.add(Arrays.asList(0, 2));
        paramList.add(Arrays.asList(4, 2));
        paramList.add(Arrays.asList(4, 3));
        paramList.add(Arrays.asList(3, 0));
        paramList.add(Arrays.asList(4, 0));
        List<List<Integer>> lists = solution.criticalConnections(7, paramList);
        System.out.println(lists);
    }

    class Solution {
        int totalNumber = 1;
        List<List<Integer>> resultList = new ArrayList<>();

        class PointInfo {
            public List<Integer> nextPointList = new ArrayList<>();
            public Integer number = 0;
            public Integer thisIndex;
        }

        public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
            Map<Integer, PointInfo> pointMap = new HashMap<>(n);
            for (List<Integer> connect : connections) {
                build(pointMap, connect.get(0), connect.get(1));
                build(pointMap, connect.get(1), connect.get(0));
            }
            find(pointMap.get(0), pointMap, null);

            return resultList;
        }

        private int find(PointInfo pointInfo, Map<Integer, PointInfo> pointMap, PointInfo father) {
            if (pointInfo.number == 0) {
                pointInfo.number = totalNumber;
                totalNumber++;
            } else {
                //不是第一次来了，直接返回
                return pointInfo.number;
            }
            int number = pointInfo.number;
            for (Integer next : pointInfo.nextPointList) {
                PointInfo nextPointInfo = pointMap.get(next);
                if (Objects.nonNull(father) && nextPointInfo.thisIndex.equals(father.thisIndex)) {
                    //如果father 和 next 相等就跳过
                    continue;
                }
                int nextResult = find(nextPointInfo, pointMap, pointInfo);
                if (nextResult < pointInfo.number)  {
                    pointInfo.number = nextResult;
                }
            }
            if (Objects.nonNull(father) && pointInfo.number == number){
                resultList.add(Arrays.asList(father.thisIndex,pointInfo.thisIndex));
            }
            return pointInfo.number;
        }

        private void build(Map<Integer, PointInfo> pointMap, Integer integer1, Integer integer2) {
            PointInfo pointInfo = pointMap.get(integer1);
            if (Objects.isNull(pointInfo)) {
                pointInfo = new PointInfo();
                pointInfo.thisIndex = integer1;
                pointMap.put(integer1, pointInfo);

            }
            pointInfo.nextPointList.add(integer2);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}