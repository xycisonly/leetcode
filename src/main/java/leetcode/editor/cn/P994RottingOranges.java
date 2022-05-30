//在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一： 
//
// 
// 值 0 代表空单元格； 
// 值 1 代表新鲜橘子； 
// 值 2 代表腐烂的橘子。 
// 
//
// 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。 
//
// 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：grid = [[2,1,1],[1,1,0],[0,1,1]]
//输出：4
// 
//
// 示例 2： 
//
// 
//输入：grid = [[2,1,1],[0,1,1],[1,0,1]]
//输出：-1
//解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
// 
//
// 示例 3： 
//
// 
//输入：grid = [[0,2]]
//输出：0
//解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 10 
// grid[i][j] 仅为 0、1 或 2 
// 
// Related Topics 广度优先搜索 数组 矩阵 👍 560 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P994RottingOranges {
    public static void main(String[] args) {
        Solution solution = new P994RottingOranges().new Solution();
        System.out.println(solution.orangesRotting(new int[][]{{2,1,1},{1,1,0},{0,1,1}}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int orangesRotting(int[][] grid) {
            if (null == grid) {
                return -1;
            }
            List<List<Integer>> newRotApple = new ArrayList<>();
            int unrottenApple = 0;

            //初始化跟节点
            for (int abscissaIndex = 0; abscissaIndex < grid.length; abscissaIndex++) {
                int[] abscissaArray = grid[abscissaIndex];
                for (int ordinateIndex = 0; ordinateIndex < abscissaArray.length; ordinateIndex++) {
                    int apple = grid[abscissaIndex][ordinateIndex];
                    if (1 == apple) {
                        unrottenApple++;
                    } else if (2 == apple) {
                        newRotApple.add(Arrays.asList(abscissaIndex, ordinateIndex));
                    }
                }
            }
            //便利到没有新腐烂水果
            int result = -1;
            while (!newRotApple.isEmpty()) {
                List<List<Integer>> newList = new ArrayList<>();
                for (int index = 0;index<newRotApple.size();index++){
                    List<Integer> integers = newRotApple.get(index);
                    Integer abscissa = integers.get(0);
                    Integer ordinate = integers.get(1);
                    unrottenApple += rot(abscissa - 1, ordinate, grid, newList);
                    unrottenApple += rot(abscissa + 1, ordinate, grid, newList);
                    unrottenApple += rot(abscissa, ordinate + 1, grid, newList);
                    unrottenApple += rot(abscissa, ordinate + -1, grid, newList);
                }
                result++;
                newRotApple = newList;
            }
            //查看是否存在未
            result = result > -1 ? result : 0;
            return unrottenApple == 0 ? result : -1;
        }

        private int rot(int abscissa, int ordinate, int[][] grid, List<List<Integer>> newRotApple) {
            if (abscissa < 0 || abscissa >= grid.length || ordinate < 0 || ordinate >= grid[0].length) {
                return 0;
            }
            if (1 != grid[abscissa][ordinate]) {
                return 0;
            }
            grid[abscissa][ordinate] = 2;
            newRotApple.add(Arrays.asList(abscissa , ordinate));
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}