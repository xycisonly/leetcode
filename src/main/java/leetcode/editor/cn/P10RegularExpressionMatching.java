//给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。 
//
// 
// '.' 匹配任意单个字符 
// '*' 匹配零个或多个前面的那一个元素 
// 
//
// 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。 
// 
//
// 示例 1： 
//
// 
//输入：s = "aa", p = "a"
//输出：false
//解释："a" 无法匹配 "aa" 整个字符串。
// 
//
// 示例 2: 
//
// 
//输入：s = "aa", p = "a*"
//输出：true
//解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
// 
//
// 示例 3： 
//
// 
//输入：s = "ab", p = ".*"
//输出：true
//解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 20 
// 1 <= p.length <= 30 
// s 只包含从 a-z 的小写字母。 
// p 只包含从 a-z 的小写字母，以及字符 . 和 *。 
// 保证每次出现字符 * 时，前面都匹配到有效的字符 
// 
// Related Topics 递归 字符串 动态规划 👍 3094 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P10RegularExpressionMatching {
    public static void main(String[] args) {
        Solution solution = new P10RegularExpressionMatching().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /***
         * 双指针+动态规划
         * @param s
         * @param p
         * @return
         */
        public boolean isMatch(String s, String p) {
            boolean[][] cache = new boolean[s.length() + 1][p.length() + 1];
            cache[0][0] = true;
            for (int sIndexAdd = 0; sIndexAdd <= s.length(); sIndexAdd++) {
                for (int pIndexAdd = 1; pIndexAdd <= p.length(); pIndexAdd++) {
                    if (p.charAt(pIndexAdd - 1) == '*') {
                        if (cache[sIndexAdd][pIndexAdd - 2]) {
                            cache[sIndexAdd][pIndexAdd] = true;
                            continue;
                        }
                        if (sIndexAdd > 0 && equal(p.charAt(pIndexAdd - 2), s.charAt(sIndexAdd - 1))) {
                            cache[sIndexAdd][pIndexAdd] = cache[sIndexAdd - 1][pIndexAdd];
                        }
                    } else {
                        if (sIndexAdd != 0 && cache[sIndexAdd - 1][pIndexAdd - 1]) {
                            cache[sIndexAdd][pIndexAdd] =
                                    equal(p.charAt(pIndexAdd - 1), s.charAt(sIndexAdd - 1));
                        }
                    }
                }

            }
            return cache[s.length()][p.length()];
        }

        public boolean isMatch1(String s, String p) {
            return doMatch(s, p, 0, 0);
        }

        /**
         * 使用双指针
         * @param s
         * @param p
         * @param sIndex
         * @param pIndex
         * @return
         */
        public boolean doMatch(String s, String p, int sIndex, int pIndex) {
            while (sIndex < s.length() && pIndex < p.length()) {
                char pChar = p.charAt(pIndex);
                if (pIndex == p.length() - 1 || p.charAt(pIndex + 1) != '*') {
                    if (equal(pChar, s.charAt(sIndex))) {
                        sIndex++;
                        pIndex++;
                    } else {
                        return false;
                    }
                } else {
                    char prevSChar = pChar;
                    for (int nSIndex = sIndex; nSIndex <= s.length(); nSIndex++) {
                        if (!equal(pChar, prevSChar)) {
                            break;
                        }

                        if (doMatch(s, p, nSIndex, pIndex + 2)) {
                            System.out.println(nSIndex + "   " + pIndex + 2);
                            return true;
                        }
                        if (nSIndex < s.length()) {
                            prevSChar = s.charAt(nSIndex);
                        }
                    }
                    return false;
                }
            }
            while (pIndex < p.length()) {
                if (pIndex + 1 < p.length() && '*' == p.charAt(pIndex + 1)) {
                    pIndex += 2;

                } else {
                    break;
                }
            }
            return sIndex == s.length() && pIndex == p.length();
        }

        public boolean equal(char pChar, char sChar) {
            if (pChar == '.') {
                return true;
            }
            return pChar == sChar;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}