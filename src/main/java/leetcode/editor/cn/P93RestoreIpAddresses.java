//有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。 
//
// 
// 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 
//和 "192.168@1.1" 是 无效 IP 地址。 
// 
//
// 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新
//排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "25525511135"
//输出：["255.255.11.135","255.255.111.35"]
// 
//
// 示例 2： 
//
// 
//输入：s = "0000"
//输出：["0.0.0.0"]
// 
//
// 示例 3： 
//
// 
//输入：s = "101023"
//输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 20 
// s 仅由数字组成 
// 
// Related Topics 字符串 回溯 👍 973 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P93RestoreIpAddresses {
    public static void main(String[] args) {
        Solution solution = new P93RestoreIpAddresses().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> restoreIpAddresses(String s) {
            List<String> result = new ArrayList<>();
            if (Objects.isNull(s) || s.length() < 4) {
                return result;
            }
            restoreIpAddresses(s, 0, result, new LinkedList<>());
            return result;
        }

        public void restoreIpAddresses(String s, int beginIndex, List<String> resultList, LinkedList<Integer> cache) {
            if (cache.size() == 4) {
                if (beginIndex == s.length()) {
                    StringBuilder result = new StringBuilder();
                    for (int index = 0; index < 4; index++) {
                        result.append(cache.get(index)).append(".");
                    }
                    result.setLength(result.length() - 1);
                    resultList.add(result.toString());
                }
                return;
            }
            if (beginIndex>=s.length()){
                return;
            }
            //如果第一个值为0 只能是0
            if (s.charAt(beginIndex)=='0'){
                cache.add(0);
                restoreIpAddresses(s, beginIndex + 1, resultList, cache);
                cache.removeLast();
                return;
            }

            //回溯构建
            
            for (int num = 1; num < 4 && beginIndex + num <= s.length(); num++) {
                String current = s.substring(beginIndex, beginIndex + num);
                int currentInt = Integer.parseInt(current);
                if (num==3&&currentInt > 255) {
                    continue;
                }

                cache.add(currentInt);
                restoreIpAddresses(s, beginIndex + num, resultList, cache);
                cache.removeLast();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}