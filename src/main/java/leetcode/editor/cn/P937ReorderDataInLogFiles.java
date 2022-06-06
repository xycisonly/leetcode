//给你一个日志数组 logs。每条日志都是以空格分隔的字串，其第一个字为字母与数字混合的 标识符 。 
//
// 有两种不同类型的日志： 
//
// 
// 字母日志：除标识符之外，所有字均由小写字母组成 
// 数字日志：除标识符之外，所有字均由数字组成 
// 
//
// 请按下述规则将日志重新排序： 
//
// 
// 所有 字母日志 都排在 数字日志 之前。 
// 字母日志 在内容不同时，忽略标识符后，按内容字母顺序排序；在内容相同时，按标识符排序。 
// 数字日志 应该保留原来的相对顺序。 
// 
//
// 返回日志的最终顺序。 
//
// 
//
// 示例 1： 
//
// 
//输入：logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 a
//rt zero"]
//输出：["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6
//"]
//解释：
//字母日志的内容都不同，所以顺序为 "art can", "art zero", "own kit dig" 。
//数字日志保留原来的相对顺序 "dig1 8 1 5 1", "dig2 3 6" 。
// 
//
// 示例 2： 
//
// 
//输入：logs = ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
//
//输出：["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= logs.length <= 100 
// 3 <= logs[i].length <= 100 
// logs[i] 中，字与字之间都用 单个 空格分隔 
// 题目数据保证 logs[i] 都有一个标识符，并且在标识符之后至少存在一个字 
// 
// Related Topics 数组 字符串 排序 
// 👍 190 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P937ReorderDataInLogFiles {
    public static void main(String[] args) {
        Solution solution = new P937ReorderDataInLogFiles().new Solution();
        System.out.println(Integer.compare(1,2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        class Bean{
            public String first;
            public String other;
            public boolean number;
        }
        Map<String,Bean> cache = new HashMap();
        public String[] reorderLogFiles(String[] logs) {
            if (Objects.isNull(logs) || logs.length == 0) {
                return null;
            }
            Arrays.sort(logs, this::sort);
            return logs;
        }

        private int sort(String str1, String str2) {
            Bean str1Cache = get(str1);
            Bean str2Cache = get(str2);

            if (str1Cache.number && !str2Cache.number) {
                return 1;
            }
            if (!str1Cache.number && str2Cache.number) {
                return -1;
            }
            if (str1Cache.number && str2Cache.number) {
                return 0;
            }
            int otherResult = str1Cache.other.compareTo(str2Cache.other);
            if (otherResult !=0){
                return otherResult;
            }
            return str1Cache.first.compareTo(str2Cache.first);
        }

        private Bean get(String str){
            Bean str1Cache = cache.get(str);
            if (Objects.isNull(str1Cache)){
                str1Cache = new Bean();
                int str1Index = str.indexOf(" ");
                str1Cache.first = str.substring(0, str1Index);
                str1Cache.other = str.substring(str1Index);
                str1Cache.number = Character.isDigit(str.charAt(str1Index + 1));
                cache.put(str,str1Cache);
            }
            return str1Cache;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}