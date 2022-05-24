//给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
//
// 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "bbbab"
//输出：4
//解释：一个可能的最长回文子序列为 "bbbb" 。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出：2
//解释：一个可能的最长回文子序列为 "bb" 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 👍 799 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P516LongestPalindromicSubsequence {
    public static void main(String[] args) {
        Solution solution = new P516LongestPalindromicSubsequence().new Solution();
        int aaa = solution.longestPalindromeSubseq("123");
        System.out.println(aaa);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
//        public int longestPalindromeSubseq(String s) {
//            int n = s.length();
//            int[][] dp = new int[n][n];
//            for (int i = n - 1; i >= 0; i--) {
//                dp[i][i] = 1;
//                char c1 = s.charAt(i);
//                for (int j = i + 1; j < n; j++) {
//                    char c2 = s.charAt(j);
//                    if (c1 == c2) {
//                        dp[i][j] = dp[i + 1][j - 1] + 2;
//                    } else {
//                        dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
//                    }
//                }
//            }
//            return dp[0][n - 1];
//        }

        public int longestPalindromeSubseq(String s) {
            int length = s.length();
            int[][] resultArray = new int[length][length];
            for (int right = 0; right < length; right++) {
                char rightChar = s.charAt(right);
                resultArray[right][right] = 1;
                for (int left = right - 1; left >= 0; left--) {
                    if (s.charAt(left) == rightChar) {
                        resultArray[left][right] = resultArray[left + 1][right - 1] + 2;
                    } else {
                        resultArray[left][right] = Math.max(resultArray[left + 1][right], resultArray[left][right - 1]);
                    }
                }
            }
            return resultArray[0][length - 1];
        }

        /**
         * 尝试使用中心扩散的方式，目前失败，时间计算太长
         *
         * @param s
         * @return
         */
        public int longestPalindromeSubseq1(String s) {
            if (null == s) {
                return 0;
            }
            int result = 0;
            for (int centerIndex = 0; centerIndex < s.length(); centerIndex++) {
                int expand1 = expand(centerIndex, centerIndex, s) - 1;
                int expand2 = expand(centerIndex, centerIndex + 1, s);
                result = Math.max(Math.max(expand1, expand2), result);
            }
            return result;
        }

        private int expand(int begin, int end, String s) {
            int result = 0;
            while (begin >= 0 && end < s.length()) {
                if (s.charAt(begin) == s.charAt(end)) {
                    result = result + 2;
                    begin--;
                    end++;
                } else {
                    int changeEndExpand = expand(begin, end + 1, s);
                    int changeBeginExpand = expand(begin - 1, end, s);
                    result = result + (changeEndExpand > changeBeginExpand ? changeEndExpand : changeBeginExpand);
                    break;
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}