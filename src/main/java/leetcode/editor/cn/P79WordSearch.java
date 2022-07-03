//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 示例 1： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = 
//"ABCCED"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = 
//"SEE"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = 
//"ABCB"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n = board[i].length 
// 1 <= m, n <= 6 
// 1 <= word.length <= 15 
// board 和 word 仅由大小写英文字母组成 
// 
//
// 
//
// 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？ 
// Related Topics 数组 回溯 矩阵 👍 1342 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P79WordSearch {
    public static void main(String[] args) {
        Solution solution = new P79WordSearch().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean exist(char[][] board, String word) {
            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board[0].length; y++) {
                    if(doExist(board, x, y, word, 0, new boolean[board.length][board[0].length])){
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean doExist(char[][] board, int x, int y, String word, int index, boolean[][] used) {
            if (used[x][y]) {
                return false;
            }
            if (board[x][y] != word.charAt(index)) {
                return false;
            }
            if (index+1 == word.length()) {
                return true;
            }
            used[x][y] = true;
            if (x + 1 < board.length) {
                if (doExist(board, x + 1, y, word, index + 1, used)) {
                    return true;
                }
            }
            if (x - 1 >= 0) {
                if (doExist(board, x - 1, y, word, index + 1, used)) {
                    return true;
                }
            }
            if (y + 1 < board[0].length) {
                if (doExist(board, x, y + 1, word, index + 1, used)) {
                    return true;
                }
            }
            if (y - 1 >= 0) {
                if (doExist(board, x, y - 1, word, index + 1, used)) {
                    return true;
                }
            }
            used[x][y] = false;
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}