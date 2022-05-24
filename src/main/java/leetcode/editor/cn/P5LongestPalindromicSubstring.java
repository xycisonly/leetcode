//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。 
//
// 示例 1： 
//
// 输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案。
// 
//
// 示例 2： 
//
// 输入: "cbbd"
//输出: "bb"
// 
// Related Topics 字符串 动态规划

package leetcode.editor.cn;

import java.util.*;

public class P5LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new P5LongestPalindromicSubstring().new Solution();
        System.out.println(solution.longestPalindrome("babad"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestPalindrome(String s) {
            if (s == null || s.length() < 2) {
                return s;
            }
            int resultLeft = 0;
            int resultRight = 0;
            int len = s.length();
            boolean[][] resultValue = new boolean[len][len];
//            for (int i = 0; i < len; i++) {
//                resultValue[i][i] = true;
//            }
            for (int right = 0; right < s.length(); right++) {
                resultValue[right][right] = true;
                char rightChar = s.charAt(right);
                for (int left = right - 1; left >= 0; left--) {
                    if (right - left < 3) {
                        resultValue[right][left] = (rightChar == s.charAt(left));
                    } else {
                        resultValue[right][left] = (rightChar == s.charAt(left)) && resultValue[right - 1][left + 1];
                    }
                    if (resultValue[right][left] && resultRight - resultLeft < right - left) {
                        resultRight = right;
                        resultLeft = left;
                    }
                }
            }
            return s.substring(resultLeft, resultRight + 1);
        }

        public String longestPalindrome1(String s) {
            if (s.length() == 0) {
                return "";
            }

        }
//        public String longestPalindrome(String s) {
//            if (s.length() == 0) {
//                return "";
//            }
//            int mBefore = 0;
//            int mAfter = 0;
//            int len = 1;
//            HashMap<Character, Integer> map = new HashMap<>();
//            for (int index = 0; index < s.length(); index++) {
//                int before = index - 1;
//                int after = index + 1;
//                if (index + len / 2 >= s.length()) {
//                    break;
//                }
//                while (before >= 0 && after < s.length()) {
//                    if (s.charAt(before) != s.charAt(after)) {
//                        break;
//                    } else {
//                        before--;
//                        after++;
//                    }
//                }
//                if (len < after - before - 1) {
//                    len = after - before - 1;
//                    mAfter = after - 1;
//                    mBefore = before + 1;
//                }
//
//                before = index - 1;
//                after = index;
//                while (before >= 0 && after < s.length()) {
//                    if (s.charAt(before) != s.charAt(after)) {
//                        break;
//                    } else {
//                        before--;
//                        after++;
//                    }
//                }
//                if (len < after - before - 1) {
//                    len = after - before - 1;
//                    mAfter = after - 1;
//                    mBefore = before + 1;
//                }
//            }
//            return s.substring(mBefore, mAfter + 1);
//        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}