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

public class P5LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new P5LongestPalindromicSubstring().new Solution();
        System.out.println(solution.longestPalindrome("1b1"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestPalindrome2(String s) {
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

        public String longestPalindrome(String s) {
            if (s.length() == 0) {
                return "";
            }
            int left = 0,right = 0;
            for (int centerIndex = 0; centerIndex < s.length(); centerIndex++) {
                int len1 = findLengthByCenter(centerIndex, centerIndex, s);
                int len2 = findLengthByCenter(centerIndex, centerIndex + 1, s);
                int len = Math.max(len1,len2);
                if (len > right-left){
                    //0 center-0 center+0 1 center-0 center+1 2 center-1 center+1 3 center-1 center+2
                    left = centerIndex - len/2;
                    right = centerIndex + (len+1)/2;
                }
            }
            return s.substring(left,right+1);
        }

        private int findLengthByCenter(int left, int right, String s) {
            while (left >= 0 && right < s.length()&&s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            return right-left-2;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}