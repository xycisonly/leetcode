//给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 10 
// -100 <= matrix[i][j] <= 100 
// 
// Related Topics 数组 矩阵 模拟 👍 1136 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P54SpiralMatrix {
    public static void main(String[] args) {
        Solution solution = new P54SpiralMatrix().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 找规律模拟
         * @param matrix
         * @return
         */
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> result = new ArrayList<>();
            int top = 0;
            int bottom = matrix.length - 1;
            int left = 0;
            int right = matrix[0].length - 1;
            while (top <= bottom && left <= right) {
                for (int x = left; x <= right; x++) {
                    result.add(matrix[top][x]);
                }
                for (int y = top + 1; y <= bottom; y++) {
                    result.add(matrix[y][right]);
                }
                if (top < bottom && left < right) {
                    for (int x = right - 1; x >= left; x--) {
                        result.add(matrix[bottom][x]);
                    }
                    for (int y = bottom - 1; y > top; y--) {
                        result.add(matrix[y][left]);
                    }
                }
                top++;
                bottom--;
                left++;
                right--;
            }
            return result;
        }

        /**
         * 根据状态做出判断，模拟
         *
         * @param matrix
         * @return
         */
        public List<Integer> spiralOrder1(int[][] matrix) {
            List<Integer> result = new ArrayList<>();
            int[][] statusArrays = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            int status = 0;
            int x = 0;
            int y = -1;
            while (result.size() < matrix.length * matrix[0].length) {
                int nx = x + statusArrays[status][0];
                int ny = y + statusArrays[status][1];
                if (nx < 0 || nx >= matrix.length ||
                        ny < 0 || ny >= matrix[0].length ||
                        matrix[nx][ny] == Integer.MAX_VALUE) {
                    status++;
                    status = (status == 4 ? 0 : status);
                    nx = x + statusArrays[status][0];
                    ny = y + statusArrays[status][1];
                }
                x = nx;
                y = ny;
                result.add(matrix[x][y]);
                matrix[x][y] = Integer.MAX_VALUE;
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}