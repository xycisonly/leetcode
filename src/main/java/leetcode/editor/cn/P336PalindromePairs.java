//给定一组 互不相同 的单词， 找出所有 不同 的索引对 (i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。 
//
// 
//
// 示例 1： 
//
// 
//输入：words = ["abcd","dcba","lls","s","sssll"]
//输出：[[0,1],[1,0],[3,2],[2,4]] 
//解释：可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
// 
//
// 示例 2： 
//
// 
//输入：words = ["bat","tab","cat"]
//输出：[[0,1],[1,0]] 
//解释：可拼接成的回文串为 ["battab","tabbat"] 
//
// 示例 3： 
//
// 
//输入：words = ["a",""]
//输出：[[0,1],[1,0]]
// 
// 
//
// 提示： 
//
// 
// 1 <= words.length <= 5000 
// 0 <= words[i].length <= 300 
// words[i] 由小写英文字母组成 
// 
// Related Topics 字典树 数组 哈希表 字符串 👍 306 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P336PalindromePairs {
    public static void main(String[] args) {
        Solution solution = new P336PalindromePairs().new Solution();
        List<List<Integer>> lists = solution.palindromePairs(new String[]{"",""});
        System.out.println(lists);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private HashMap<String, Integer> map = new HashMap<>();

        public List<List<Integer>> palindromePairs(String[] words) {
            //构建反序map
            for (int index = 0; index < words.length; index++) {
                String reverse = new StringBuilder(words[index]).reverse().toString();
                map.put(reverse, index);
            }
            List<List<Integer>> resultList = new ArrayList<>();
            for (int index = 0; index < words.length; index++) {
                String indexWord = words[index];
                for (int charIndex = 0; charIndex <= indexWord.length(); charIndex++) {
                    if (charIndex !=0 && palindromeString(0, charIndex - 1, indexWord)) {
                        //charIndex+1 越界问题
                        String substring = indexWord.substring(charIndex, indexWord.length());
                        Integer resultIndex = map.get(substring);
                        if (Objects.nonNull(resultIndex) && resultIndex != index) {
                            resultList.add(Arrays.asList(resultIndex, index));
                        }
                    }
                    if (palindromeString(charIndex, indexWord.length() - 1, indexWord)) {
                        String substring;
                        if (charIndex <= 0) {
                            substring = "";
                        } else {
                            substring = indexWord.substring(0, charIndex);
                        }
                        Integer resultIndex = map.get(substring);
                        if (Objects.nonNull(resultIndex) && resultIndex != index) {
                            resultList.add(Arrays.asList(index, resultIndex));
                        }
                    }
                }
            }
            return resultList;
        }

        private boolean palindromeString(int begin, int end, String str) {
            //边界校验 0 -1 是回文字 length length-1 是回文字
            if (begin > end) {
                return true;
            }
            while (begin < end) {
                if (str.charAt(begin) != str.charAt(end)) {
                    return false;
                }
                begin++;
                end--;
            }
            return true;
        }
List<String> wordsRev = new ArrayList<String>();
        Map<String, Integer> indices = new HashMap<String, Integer>();

        public List<List<Integer>> palindromePairs2(String[] words) {
            int n = words.length;
            for (String word: words) {
                wordsRev.add(new StringBuffer(word).reverse().toString());
            }
            for (int i = 0; i < n; ++i) {
                indices.put(wordsRev.get(i), i);
            }

            List<List<Integer>> ret = new ArrayList<List<Integer>>();
            for (int i = 0; i < n; i++) {
                String word = words[i];
                int m = words[i].length();
                if (m == 0) {
                    continue;
                }
                for (int j = 0; j <= m; j++) {
                    if (isPalindrome(word, j, m - 1)) {
                        int leftId = findWord(word, 0, j - 1);
                        if (leftId != -1 && leftId != i) {
                            ret.add(Arrays.asList(i, leftId));
                        }
                    }
                    if (j != 0 && isPalindrome(word, 0, j - 1)) {
                        int rightId = findWord(word, j, m - 1);
                        if (rightId != -1 && rightId != i) {
                            ret.add(Arrays.asList(rightId, i));
                        }
                    }
                }
            }
            return ret;
        }

        public boolean isPalindrome(String s, int left, int right) {
            int len = right - left + 1;
            for (int i = 0; i < len / 2; i++) {
                if (s.charAt(left + i) != s.charAt(right - i)) {
                    return false;
                }
            }
            return true;
        }

        public int findWord(String s, int left, int right) {
            return indices.getOrDefault(s.substring(left, right + 1), -1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}