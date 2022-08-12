//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：s = "(()"
//输出：2
//解释：最长有效括号子串是 "()"
// 
//
// 示例 2： 
//
// 
//输入：s = ")()())"
//输出：4
//解释：最长有效括号子串是 "()()"
// 
//
// 示例 3： 
//
// 
//输入：s = ""
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3 * 10⁴ 
// s[i] 为 '(' 或 ')' 
// 
// 
// 
// Related Topics 栈 字符串 动态规划 👍 1934 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P32LongestValidParentheses {
    public static void main(String[] args) {
        Solution solution = new P32LongestValidParentheses().new Solution();
        System.out.println(solution.longestValidParentheses("(()()"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 双记录，为了处理(()情况不被记录问题，需要反方向进行一次
         *
         * @param s
         * @return
         */
        public int longestValidParentheses(String s) {
            int left = 0, right = 0, result = 0;
            for (int index = 0; index < s.length(); index++) {
                if (s.charAt(index) == '(') {
                    left++;
                } else {
                    right++;
                }
                if (left == right) {
                    result = Math.max(result, 2 * left);
                } else if (left < right) {
                    left = 0;
                    right = 0;
                }
            }
            left = right = 0;
            for (int index = s.length() - 1; index >= 0; index--) {
                if (s.charAt(index) == ')') {
                    left++;
                } else {
                    right++;
                }
                if (left == right) {
                    result = Math.max(result, 2 * left);
                } else if (left < right) {
                    left = 0;
                    right = 0;
                }
            }
            return result;
        }

        /**
         * 使用栈，保证
         * @param s
         * @return
         */
        public int longestValidParentheses2(String s) {
            int result = 0;
            LinkedList<Integer> cache = new LinkedList<>();
            cache.addFirst(-1);
            for (int index = 0; index < s.length(); index++) {
                if (s.charAt(index) == '(') {
                    cache.addFirst(index);
                } else {
                    cache.removeFirst();
                    if (!cache.isEmpty()) {
                        Integer integer = cache.peekFirst();
                        result = Math.max(result, index - integer);
                    } else {
                        cache.addFirst(index);
                    }
                }
            }
            return result;
        }

        public int longestValidParentheses1(String s) {
            int result = 0;
            int[] cache = new int[s.length()];
            for (int index = 1; index < s.length(); index++) {
                if (s.charAt(index) == '(') {
                    continue;
                }
                int compareIndex = index - 1 - cache[index - 1];
                if (compareIndex < 0 || s.charAt(compareIndex) != '(') {
                    continue;
                }
                if (compareIndex > 0) {
                    cache[index] = cache[index - 1] + 2 + cache[compareIndex - 1];
                } else {
                    cache[index] = cache[index - 1] + 2;
                }
                result = Math.max(cache[index], result);
            }
            return result;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}