//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["flower","flow","flight"]
//输出："fl"
// 
//
// 示例 2： 
//
// 
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] 仅由小写英文字母组成 
// 
// Related Topics 字符串 👍 2373 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P14LongestCommonPrefix {
    public static void main(String[] args) {
        Solution solution = new P14LongestCommonPrefix().new Solution();
        solution.longestCommonPrefix(new String[]{"ab", "a"});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestCommonPrefix(String[] strs) {
            int index = 0;

            while (index < strs[0].length()) {
                char compare = strs[0].charAt(index);
                for (int innerIndex = 1; innerIndex < strs.length; innerIndex++) {
                    if (index >= strs[innerIndex].length() || strs[innerIndex].charAt(index) != compare) {
                        return strs[0].substring(0,index);
                        
                    }
                }
                index++;
            }
            return strs[0];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}