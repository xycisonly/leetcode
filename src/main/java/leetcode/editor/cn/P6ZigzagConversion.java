//将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。 
//
// 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下： 
//
// 
//P   A   H   N
//A P L S I I G
//Y   I   R 
//
// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。 
//
// 请你实现这个将字符串进行指定行数变换的函数： 
//
// 
//string convert(string s, int numRows); 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "PAYPALISHIRING", numRows = 3
//输出："PAHNAPLSIIGYIR"
// 
//示例 2：
//
// 
//输入：s = "PAYPALISHIRING", numRows = 4
//输出："PINALSIGYAHRPI"
//解释：
//P     I    N
//A   L S  I G
//Y A   H R
//P     I
// 
//
// 示例 3： 
//
// 
//输入：s = "A", numRows = 1
//输出："A"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 由英文字母（小写和大写）、',' 和 '.' 组成 
// 1 <= numRows <= 1000 
// 
// Related Topics 字符串 👍 1718 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P6ZigzagConversion {
    public static void main(String[] args) {
        Solution solution = new P6ZigzagConversion().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public String convert(String s, int numRows) {
            if (numRows == 1 || numRows >= s.length()) {
                return s;
            }
            StringBuilder result = new StringBuilder();
            int n = numRows * 2 - 2;
            for (int y = 0; y < numRows; y++) {
                int x = 0;
                boolean b = y != 0 && y != numRows - 1;
                while (true) {
                    int index1 = n * x + y;
                    if (s.length() <= index1) {
                        break;
                    }
                    result.append(s.charAt(index1));
                    if (b) {
                        int index2 = n * (x+1) - y;
                        if (s.length() <= index2) {
                            break;
                        }
                        result.append(s.charAt(index2));
                    }
                    x++;
                }
            }
            return result.toString();
        }

        /**
         * 以s作为基准
         * 并没有比方法一更快，因为进行了更多的逻辑计算
         *
         * @param s
         * @param numRows
         * @return
         */
        public String convert2(String s, int numRows) {
            int length = s.length();
            if (numRows == 1 || numRows >= length) {
                return s;
            }
            StringBuilder[] mat = new StringBuilder[numRows];
            for (int i = 0; i < numRows; ++i) {
                mat[i] = new StringBuilder();
            }
            for (int i = 0, x = 0, t = numRows * 2 - 2; i < length; ++i) {
                mat[x].append(s.charAt(i));
                if (i % t < numRows - 1) {
                    ++x;
                } else {
                    --x;
                }
            }
            StringBuilder ans = new StringBuilder();
            for (StringBuilder row : mat) {
                ans.append(row);
            }
            return ans.toString();
        }

        /**
         * 以cache作为基准
         *
         * @param s
         * @param numRows
         * @return
         */
        public String convert1(String s, int numRows) {
            if (numRows == 1 || numRows >= s.length()) {
                return s;
            }
            int cacheXLen = 2 * s.length() / (2 * numRows - 2) + (2 * s.length() % (2 * numRows - 2) == 0 ? 0 : 2);
            char[][] cache = new char[cacheXLen][numRows];
            int sIndex = 0;
            for (int xIndex = 0; xIndex < cacheXLen; xIndex++) {
                char[] cacheX = cache[xIndex];
                boolean a = xIndex % 2 == 0;
                for (int yIndex = 0; yIndex < numRows; yIndex++) {
                    if (a) {
                        cacheX[yIndex] = s.charAt(sIndex);
                    } else {
                        if (yIndex == 0 || yIndex == numRows - 1) {
                            continue;
                        }
                        cacheX[numRows - 1 - yIndex] = s.charAt(sIndex);
                    }
                    sIndex++;
                    if (sIndex == s.length()) {
                        break;
                    }
                }
                if (sIndex == s.length()) {
                    break;
                }
            }
//            System.out.println(Arrays.deepToString(cache));
            StringBuilder builder = new StringBuilder();
            for (int yIndex = 0; yIndex < numRows; yIndex++) {
                for (int xIndex = 0; xIndex < cacheXLen; xIndex++) {
                    if (cache[xIndex][yIndex] == '\u0000') {
                        continue;
                    }
                    builder.append(cache[xIndex][yIndex]);
                }
            }
            return builder.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}