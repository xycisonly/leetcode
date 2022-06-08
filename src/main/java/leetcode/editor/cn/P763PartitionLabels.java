//字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。 
//
// 
//
// 示例： 
//
// 
//输入：S = "ababcbacadefegdehijhklij"
//输出：[9,7,8]
//解释：
//划分结果为 "ababcbaca", "defegde", "hijhklij"。
//每个字母最多出现在一个片段中。
//像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
// 
//
// 
//
// 提示： 
//
// 
// S的长度在[1, 500]之间。 
// S只包含小写字母 'a' 到 'z' 。 
// 
// Related Topics 贪心 哈希表 双指针 字符串 
// 👍 741 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P763PartitionLabels {
    public static void main(String[] args) {
        Solution solution = new P763PartitionLabels().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> partitionLabels(String s) {
            int[] characterArray = new int[26];
            for (int arrayIndex = 0; arrayIndex < s.length(); arrayIndex++) {
                characterArray[s.charAt(arrayIndex)-'a'] = arrayIndex;

            }

            ArrayList<Integer> result = new ArrayList<>();
            int begin = 0;
            int end = 0;
            while (begin < s.length()) {
                for (int index = begin; index <= end; index++) {
                    int lastIndex = characterArray[s.charAt(index)-'a'];
                    if (lastIndex != index) {
                        end = Math.max(end, lastIndex);
                    }
                }

                result.add(end - begin + 1);
                begin = end + 1;
                end = begin;
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}