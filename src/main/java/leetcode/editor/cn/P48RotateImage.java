//给定一个 n × n 的二维矩阵表示一个图像。 
//
// 将图像顺时针旋转 90 度。 
//
// 说明： 
//
// 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。 
//
// 示例 1: 
//
// 给定 matrix = 
//[
//  [1,2,3],
//  [4,5,6],
//  [7,8,9]
//],
//
//原地旋转输入矩阵，使其变为:
//[
//  [7,4,1],
//  [8,5,2],
//  [9,6,3]
//]
// 
//
// 示例 2: 
//
// 给定 matrix =
//[
//  [ 5, 1, 9,11],
//  [ 2, 4, 8,10],
//  [13, 3, 6, 7],
//  [15,14,12,16]
//], 
//
//原地旋转输入矩阵，使其变为:
//[
//  [15,13, 2, 5],
//  [14, 3, 4, 1],
//  [12, 6, 8, 9],
//  [16, 7,10,11]
//]
// 
// Related Topics 数组

package leetcode.editor.cn;

import java.util.*;
  public class P48RotateImage{
      public static void main(String[] args) {
           Solution solution = new P48RotateImage().new Solution();
      }
        //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void rotate(int[][] matrix) {
        int length = matrix.length;
        int origin = length / 2 + length % 2;
        for (int index1 = 0;index1<length/2;index1++){
            for (int index2 = 0; index2< origin; index2++){
                int oldNum = matrix[index1][index2];
                for (int a = 1;a<5;a++){
                    int index11 = index2;
                    int index22 = length -1-index1;
                    int newNum = matrix[index11][index22];
                    matrix[index11][index22] = oldNum;
                    oldNum = newNum;
                    index1 = index11;
                    index2 = index22;
                }
            }
        }

    }

}
//leetcode submit region end(Prohibit modification and deletion)

  }