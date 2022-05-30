//你被给定一个 m × n 的二维网格 rooms ，网格中有以下三种可能的初始化值：
//
// 
// -1 表示墙或是障碍物 
// 0 表示一扇门 
// INF 无限表示一个空的房间。然后，我们用 2³¹ - 1 = 2147483647 代表 INF。你可以认为通往门的距离总是小于 2147483647 
//的。 
// 
//
// 你要给每个空房间位上填上该房间到 最近门的距离 ，如果无法到达门，则填 INF 即可。 
//
// 
//
// 示例 1： 
//
// 
//输入：rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1]
//,[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
//输出：[[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
// 
//
// 示例 2： 
//
// 
//输入：rooms = [[-1]]
//输出：[[-1]]
// 
//
// 示例 3： 
//
// 
//输入：rooms = [[2147483647]]
//输出：[[2147483647]]
// 
//
// 示例 4： 
//
// 
//输入：rooms = [[0]]
//输出：[[0]]
// 
//
// 
//
// 提示： 
//
// 
// m == rooms.length 
// n == rooms[i].length 
// 1 <= m, n <= 250 
// rooms[i][j] 是 -1、0 或 2³¹ - 1 
// 
// Related Topics 广度优先搜索 数组 矩阵 👍 201 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P286WallsAndGates {
    public static void main(String[] args) {
        Solution solution = new P286WallsAndGates().new Solution();
//        solution.wallsAndGates(new int[][]{{2147483647, -1, 0, 2147483647}, {2147483647, 2147483647, 2147483647, -1}, {2147483647, -1, 2147483647, -1}, {0, -1, 2147483647, 2147483647}});
        solution.wallsAndGates(new int[][]{{2147483647, 0, 2147483647, 2147483647, 0, 2147483647, -1, 2147483647}});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private static final int EMPTY = Integer.MAX_VALUE;
        private static final int GATE = 0;
        private final List<int[]> DIRECTIONS = Arrays.asList(
                new int[] { 1,  0},
                new int[] {-1,  0},
                new int[] { 0,  1},
                new int[] { 0, -1}
        );

        public void wallsAndGates(int[][] rooms) {
            int m = rooms.length;
            if (m == 0) return;
            int n = rooms[0].length;
            Queue<int[]> q = new LinkedList<>();
            for (int row = 0; row < m; row++) {
                for (int col = 0; col < n; col++) {
                    if (rooms[row][col] == GATE) {
                        q.add(new int[] { row, col });
                    }
                }
            }
            while (!q.isEmpty()) {
                int[] point = q.poll();
                int row = point[0];
                int col = point[1];
                for (int[] direction : DIRECTIONS) {
                    int r = row + direction[0];
                    int c = col + direction[1];
                    if (r < 0 || c < 0 || r >= m || c >= n || rooms[r][c] != EMPTY) {
                        continue;
                    }
                    rooms[r][c] = rooms[row][col] + 1;
                    q.add(new int[] { r, c });
                }
            }
        }

        public void wallsAndGates2(int[][] rooms) {
            if (null == rooms) {
                return;
            }
            List<List<Integer>> list = new ArrayList<>();
            for (int x = 0; x < rooms.length; x++) {
                for (int y = 0; y < rooms[0].length; y++) {
                    if (0 == rooms[x][y]) {
                        list.add(Arrays.asList(x, y));
//                        list.add(Arrays.asList(x+1,y,1));
//                        list.add(Arrays.asList(x-1,y,1));
//                        list.add(Arrays.asList(x,y+1,1));
//                        list.add(Arrays.asList(x,y-1,1));
                    }
                }
            }
            while (!list.isEmpty()) {
                List<List<Integer>> newList = new ArrayList<>();
                for (List<Integer> room : list) {
                    Integer x = room.get(0);
                    Integer y = room.get(1);
                    int len = rooms[x][y];
                    dealTarget(x + 1, y,len + 1,rooms,newList);
                    dealTarget(x - 1, y,len + 1,rooms,newList);
                    dealTarget(x, y + 1,len + 1,rooms,newList);
                    dealTarget(x, y - 1,len + 1,rooms,newList);
                }
                list = newList;
            }
        }

        private void dealTarget(int x, int y, int len, int[][] rooms,List<List<Integer>> newList) {
            if (x < 0 || y < 0 || x >= rooms.length || y >= rooms[0].length || rooms[x][y] < len) {
                return;
            }
            rooms[x][y] = len;
            newList.add(Arrays.asList(x,y));
        }

        public void wallsAndGates1(int[][] rooms) {
            if (null == rooms) {
                return;
            }

            for (int x = 0; x < rooms.length; x++) {
                for (int y = 0; y < rooms[0].length; y++) {
                    if (0 == rooms[x][y]) {
                        dealTarget1(rooms, x + 1, y, 1);
                        dealTarget1(rooms, x - 1, y, 1);
                        dealTarget1(rooms, x, y + 1, 1);
                        dealTarget1(rooms, x, y - 1, 1);
                    }
                }
            }
        }

        private void dealTarget1(int[][] rooms, int x, int y, int length) {
            if (x < 0 || y < 0 || x >= rooms.length || y >= rooms[0].length) {
                return;
            }
            int i = rooms[x][y];
            if (i <= length) {
                return;
            }
            rooms[x][y] = length;
            dealTarget1(rooms, x + 1, y, length + 1);
            dealTarget1(rooms, x - 1, y, length + 1);
            dealTarget1(rooms, x, y + 1, length + 1);
            dealTarget1(rooms, x, y - 1, length + 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
