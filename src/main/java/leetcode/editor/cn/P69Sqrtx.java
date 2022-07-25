//给你一个非负整数 x ，计算并返回 x 的 算术平方根 。 
//
// 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。 
//
// 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。 
//
// 
//
// 示例 1： 
//
// 
//输入：x = 4
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：x = 8
//输出：2
//解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= x <= 2³¹ - 1 
// 
// Related Topics 数学 二分查找 👍 1070 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P69Sqrtx {
    public static void main(String[] args) {
        Solution solution = new P69Sqrtx().new Solution();
        System.out.println(1e-7);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 牛顿快速查找
         *
         * @param x
         * @return
         */
        public int mySqrt(int x) {
            if (x == 0) {
                return 0;
            }
            double x0 = x;
            double c = x;
            while (true) {
                double x1 = 0.5 * (x0 + c / x0);
                if (Math.abs(x0 - x1) < 1e-7) {
                    break;
                }
                x0 = x1;
                System.out.println(x0);
            }
            return (int) x0;
        }

        /**
         * 二分查找
         *
         * @param x
         * @return
         */
        public int mySqrt1(int x) {
            if (x < 2) {
                return x;
            }
            int begin = 0;
            int end = x;
            while (begin != end - 1) {
                int num = (begin + end) / 2;
                long num2 = ((long) num) * num;
                if (num2 == x) {
                    return num;
                }
                if (num2 > x) {
                    end = num;
                } else {
                    begin = num;
                }
            }
            return begin;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}