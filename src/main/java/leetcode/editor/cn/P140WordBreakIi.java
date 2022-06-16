//给定一个字符串 s 和一个字符串字典 wordDict ，在字符串 s 中增加空格来构建一个句子，使得句子中所有的单词都在词典中。以任意顺序 返回所有这些可
//能的句子。 
//
// 注意：词典中的同一个单词可能在分段中被重复使用多次。 
//
// 
//
// 示例 1： 
//
// 
//输入:s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
//输出:["cats and dog","cat sand dog"]
// 
//
// 示例 2： 
//
// 
//输入:s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine",
//"pineapple"]
//输出:["pine apple pen apple","pineapple pen apple","pine applepen apple"]
//解释: 注意你可以重复使用字典中的单词。
// 
//
// 示例 3： 
//
// 
//输入:s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
//输出:[]
// 
//
// 
//
// 提示： 
//
// 
//
// 
// 1 <= s.length <= 20 
// 1 <= wordDict.length <= 1000 
// 1 <= wordDict[i].length <= 10 
// s 和 wordDict[i] 仅有小写英文字母组成 
// wordDict 中所有字符串都 不同 
// 
// Related Topics 字典树 记忆化搜索 哈希表 字符串 动态规划 回溯 👍 600 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P140WordBreakIi {
    public static void main(String[] args) {
        Solution solution = new P140WordBreakIi().new Solution();
        List<String> strings = solution.wordBreak("pineapplepenapple", Arrays.asList("apple", "pen", "applepen", "pine", "pineapple"));
        System.out.println(strings);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public List<String> wordBreak(String s, List<String> wordDict) {
            return wordBreak3(s, wordDict);
        }

        /**
         * 也是用的回溯，但是没有记录以前回溯的结果反而更快，只能呵呵了
         * @param s
         * @param wordDict
         * @return
         */
        public List<String> wordBreak3(String s, List<String> wordDict) {
            Set<String> set = new HashSet<>(wordDict);
            List<String> ans = new ArrayList<>();
            dfs(s, ans, 0, new LinkedList<>(), set);
            return ans;
        }

        public void dfs(String s, List<String> ans, int idx, Deque<String> path, Set<String> set) {
            if (idx == s.length()) {
                ans.add(String.join(" ", path));
                return;
            }
            for (int i = idx; i < s.length(); i++) {
                String str = s.substring(idx, i + 1);
                if (set.contains(str)) {
                    path.add(str);
                    dfs(s, ans, i + 1, path, set);
                    path.removeLast();
                }
            }
        }

        /**
         * wordBreak1 改进版是利用了回溯，先进行高位计算，如果高位有数据，再进行递归计算低位计算
         *
         * @param s
         * @param wordDict
         * @return
         */
        public List<String> wordBreak2(String s, List<String> wordDict) {
            List<List<String>>[] resultArray = new ArrayList[s.length() + 1];
            resultArray[0] = new ArrayList<>();
            resultArray[0].add(Collections.singletonList(""));
            List<List<String>> resultList = wordBreak2Source(s, new HashSet<>(wordDict), s.length(), resultArray);
            List<String> result = new ArrayList<>();
            if (Objects.isNull(resultList)){
                return result;
            }
            for (List<String> resultLink:resultList){
                String join = String.join(" ", resultLink);
                result.add(join.substring(1));
            }
            return result;
        }

        public List<List<String>> wordBreak2Source(String s, Set<String> wordDict, int lastIndexAddLen, List<List<String>>[] resultArray) {
            if (resultArray[lastIndexAddLen] != null) {
                return resultArray[lastIndexAddLen];
            }
            for (int innerIndex = lastIndexAddLen - 1; innerIndex >= 0; innerIndex--) {
                String currentWold = s.substring(innerIndex, lastIndexAddLen);
                if (wordDict.contains(currentWold)) {
                    //找以前的
                    List<List<String>> oldResult = wordBreak2Source(s, wordDict, innerIndex, resultArray);
                    if (Objects.isNull(oldResult)) {
                        continue;
                    }
                    //拼装放入现在的
                    List<List<String>> result = resultArray[lastIndexAddLen];
                    if (Objects.isNull(result)){
                        result = new ArrayList<>();
                        resultArray[lastIndexAddLen] = result;
                    }
                    for (List<String> oneOldResult : oldResult) {
                        LinkedList<String> copyOldResult = new LinkedList<>(oneOldResult);
                        copyOldResult.add(currentWold);
                        result.add(copyOldResult);
                    }
                }
            }
            return resultArray[lastIndexAddLen];
        }

        //参考139 的动态规划，但是有哪位先准备低位需要的数据，再进行高位计算，然而，如果高位直接不可能等于某个值，低位计算毫无意义
        public List<String> wordBreak1(String s, List<String> wordDict) {
            Set<String> wordSet = new HashSet<>(wordDict);
            List<String>[] cache = new List[s.length() + 1];
            cache[0] = new ArrayList<>();
            cache[0].add("");
            for (int index = 1; index <= s.length(); index++) {
                for (int innerIndex = 0; innerIndex < index; innerIndex++) {
                    List<String> cacheList = cache[innerIndex];
                    String targetWold = null;
                    if (Objects.nonNull(cacheList) && wordSet.contains(targetWold = s.substring(innerIndex, index))) {
                        List<String> currentResult = cache[index];
                        if (Objects.isNull(currentResult)) {
                            currentResult = new ArrayList<>();
                            cache[index] = currentResult;
                        }
                        for (String cacheResult : cacheList) {
                            if ("".equals(cacheResult)) {
                                currentResult.add(cacheResult + targetWold);
                            } else {
                                currentResult.add(cacheResult + " " + targetWold);
                            }

                        }
                    }
                }
            }
            return Objects.isNull(cache[s.length()]) ? new ArrayList<>() : cache[s.length()];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}