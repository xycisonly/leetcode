//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1: 
//
// 输入:
//[
//['1','1','1','1','0'],
//['1','1','0','1','0'],
//['1','1','0','0','0'],
//['0','0','0','0','0']
//]
//输出: 1
// 
//
// 示例 2: 
//
// 输入:
//[
//['1','1','0','0','0'],
//['1','1','0','0','0'],
//['0','0','1','0','0'],
//['0','0','0','1','1']
//]
//输出: 3
//解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 694 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P200NumberOfIslands {
    public static void main(String[] args) {
        Solution solution = new P200NumberOfIslands().new Solution();
        solution.numIslands(new char[][]{{'1', '1', '1'}, {'0', '1', '0'}, {'1', '1', '1'}});
    }

    // 1 1 0
    // 0 1 0
    // 1 1 1
    // 0 1 0
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //0 1 1 1 1 0
        //0 1 0 1 0 0
        //1 1 1 1 1 0
        //0 0 0 1 0 0
        public int numIslands(char[][] grid) {
            int result = 0;

            for (int index1 = 0; index1 < grid.length; index1++) {
                char[] grid1 = grid[index1];
                for (int index2 = 0; index2 < grid1.length; index2++) {
                    if (grid[index1][index2] == '1') {
                        result++;
                        change(grid, index1, index2);
                    }
                }
            }
            return result;
        }

        private void change(char[][] grid, int index1, int index2) {
            if (grid[index1][index2] != '1') {
                return;
            } else {
                grid[index1][index2] = '2';
            }
            if (index1 > 0) change(grid, index1 - 1, index2);
            if (index1 < grid.length - 1) change(grid, index1 + 1, index2);
            if (index2 > 0) change(grid, index1, index2 - 1);
            if (index2 < grid[index1].length - 1) change(grid, index1, index2 + 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}