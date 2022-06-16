//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
//
//
//
// 示例:
//
// 输入："23"
//输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
//
//
// 说明:
//尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
// Related Topics 字符串 回溯算法

package leetcode.editor.cn;


import java.util.*;

public class P17LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        Solution solution = new P17LetterCombinationsOfAPhoneNumber().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> letterCombinations(String digits) {
            return letterCombinations2(digits);
        }

        /**
         * 回溯法
         * @param digits
         * @return
         */
        public List<String> letterCombinations2(String digits) {
            if ("".equals(digits)){
                return new ArrayList<>();
            }
            Map<Character,List<String>> map = new HashMap<>();
            map.put('2',Arrays.asList("a","b","c"));
            map.put('3',Arrays.asList("d","e","f"));
            map.put('4',Arrays.asList("g","h","i"));
            map.put('5',Arrays.asList("j","k","l"));
            map.put('6',Arrays.asList("m","n","o"));
            map.put('7',Arrays.asList("p","q","r","s"));
            map.put('8',Arrays.asList("t","u","v"));
            map.put('9',Arrays.asList("w","x","y","z"));
            LinkedList<String> result = new LinkedList<>();
            letterCombinations2(digits,0,map,result,new StringBuilder());
            return result;
        }

        private void letterCombinations2(String digits,int index ,Map<Character,List<String>> map,
                                         List<String> resultList,StringBuilder sb){
            if (index==digits.length()){
                resultList.add(sb.toString());
                return;
            }
            for (String currentChar:map.get(digits.charAt(index))){
                sb.append(currentChar);

                letterCombinations2(digits,index+1,map,resultList,sb);
                sb.deleteCharAt(index);
            }
        }

        /**
         * 最原始方法没有使用回溯
         * 缺点字符串拼接次数过多
         * @param digits
         * @return
         */
        public List<String> letterCombinations1(String digits) {
            if ("".equals(digits)){
                return new ArrayList<>();
            }
            Map<Character,List<String>> map = new HashMap<>();
            map.put('2',Arrays.asList("a","b","c"));
            map.put('3',Arrays.asList("d","e","f"));
            map.put('4',Arrays.asList("g","h","i"));
            map.put('5',Arrays.asList("j","k","l"));
            map.put('6',Arrays.asList("m","n","o"));
            map.put('7',Arrays.asList("p","q","r","s"));
            map.put('8',Arrays.asList("t","u","v"));
            map.put('9',Arrays.asList("w","x","y","z"));
            List<String> resultList = new ArrayList<>();
            resultList.add("");
            for (int index = 0;index<digits.length();index++){
                List<String> cache = new ArrayList<>();
                List<String> charList = map.get(digits.charAt(index));
                for (String result:resultList){
                    for (String newChar:charList){
                        cache.add(result+newChar);
                    }
                }
                resultList = cache;
            }
            return resultList;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}