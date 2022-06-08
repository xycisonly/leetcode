//字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s
//2 -> ... -> sk： 
//
// 
// 每一对相邻的单词只差一个字母。 
// 对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。 
// sk == endWord 
// 
//
// 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 
//中的 单词数目 。如果不存在这样的转换序列，返回 0 。 
// 
//
// 示例 1： 
//
// 
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","lo
//g","cog"]
//输出：5
//解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
// 
//
// 示例 2： 
//
// 
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","lo
//g"]
//输出：0
//解释：endWord "cog" 不在字典中，所以无法进行转换。 
//
// 
//
// 提示： 
//
// 
// 1 <= beginWord.length <= 10 
// endWord.length == beginWord.length 
// 1 <= wordList.length <= 5000 
// wordList[i].length == beginWord.length 
// beginWord、endWord 和 wordList[i] 由小写英文字母组成 
// beginWord != endWord 
// wordList 中的所有字符串 互不相同 
// 
// Related Topics 广度优先搜索 哈希表 字符串 
// 👍 1047 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P127WordLadder {
    public static void main(String[] args) {
        Solution solution = new P127WordLadder().new Solution();
        solution.ladderLength("hit","cog",Arrays.asList("hot","dot","tog","cog"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    public class Solution {

        public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
            // 第 1 步：先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
            Set<String> wordSet = new HashSet<>(wordList);
            if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
                return 0;
            }
            wordSet.remove(beginWord);

            // 第 2 步：图的广度优先遍历，必须使用队列和表示是否访问过的 visited 哈希表
            Queue<String> queue = new LinkedList<>();
            queue.offer(beginWord);
            Set<String> visited = new HashSet<>();
            visited.add(beginWord);

            // 第 3 步：开始广度优先遍历，包含起点，因此初始化的时候步数为 1
            int step = 1;
            while (!queue.isEmpty()) {
                int currentSize = queue.size();
                for (int i = 0; i < currentSize; i++) {
                    // 依次遍历当前队列中的单词
                    String currentWord = queue.poll();
                    // 如果 currentWord 能够修改 1 个字符与 endWord 相同，则返回 step + 1
                    if (changeWordEveryOneLetter(currentWord, endWord, queue, visited, wordSet)) {
                        return step + 1;
                    }
                }
                step++;
            }
            return 0;
        }

        /**
         * 尝试对 currentWord 修改每一个字符，看看是不是能与 endWord 匹配
         *
         * @param currentWord
         * @param endWord
         * @param queue
         * @param visited
         * @param wordSet
         * @return
         */
        private boolean changeWordEveryOneLetter(String currentWord, String endWord,
                                                 Queue<String> queue, Set<String> visited, Set<String> wordSet) {
            char[] charArray = currentWord.toCharArray();
            for (int i = 0; i < endWord.length(); i++) {
                // 先保存，然后恢复
                char originChar = charArray[i];
                for (char k = 'a'; k <= 'z'; k++) {
                    if (k == originChar) {
                        continue;
                    }
                    charArray[i] = k;
                    String nextWord = String.valueOf(charArray);
                    if (wordSet.contains(nextWord)) {
                        if (nextWord.equals(endWord)) {
                            return true;
                        }
                        if (!visited.contains(nextWord)) {
                            queue.add(nextWord);
                            // 注意：添加到队列以后，必须马上标记为已经访问
                            visited.add(nextWord);
                        }
                    }
                }
                // 恢复
                charArray[i] = originChar;
            }
            return false;
        }

        /**
         * 使用构建图的方式创建
         *
         * @param beginWord
         * @param endWord
         * @param wordList
         * @return
         */
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            //创建代表图的map
            Map<String, Set<String>> edgeMap = new HashMap<>();
            fillEdgeMap(edgeMap, beginWord, wordList);
            if (!edgeMap.containsKey(endWord)){
                return 0;
            }
            HashSet<String> usedSet = new HashSet<>();
            //创建todo的队列
            LinkedList<String> todoList = new LinkedList<>();
            todoList.addFirst(beginWord);
            usedSet.add(beginWord);
            //最短长度
            int result = 2;
            while (!todoList.isEmpty()) {
                int todoNumber = todoList.size();
                while (todoNumber > 0) {
                    String current = todoList.removeLast();
                    Set<String> nextList = edgeMap.get(current);
                    for (String next : nextList) {
                        Set<String> targetSet = edgeMap.get(next);
                        for (String target : targetSet) {
                            if (usedSet.contains(target)){
                                continue;
                            }
                            if (target.equals(endWord)) {
                                return result;
                            }
                            todoList.addFirst(target);
                            usedSet.add(target);
                        }
                    }
                    todoNumber--;
                }
                result++;
            }
            return 0;
        }

        private void fillEdgeMap(Map<String, Set<String>> edgeMap, String beginWord, List<String> wordList) {
            List<String> dealList = new ArrayList<>(wordList);
            dealList.add(beginWord);
            for (String word : dealList) {
                addEdgeMap(word, edgeMap);
                char[] wordChar = word.toCharArray();
                for (int wordIndex = 0; wordIndex < word.length(); wordIndex++) {
                    char cache = wordChar[wordIndex];
                    wordChar[wordIndex] = '*';
                    String wordSon = String.valueOf(wordChar);
                    wordChar[wordIndex] = cache;
                    addEdgeMap(wordSon, edgeMap);
                    edgeMap.get(word).add(wordSon);
                    edgeMap.get(wordSon).add(word);
                }
            }
        }

        private void addEdgeMap(String wold, Map<String, Set<String>> edgeMap) {
            if (!edgeMap.containsKey(wold)) {
                edgeMap.put(wold, new HashSet<>());
            }
        }
    }
//    class Solution {

//    }

//    class Solution {
//        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//            int result = ladderLength(beginWord, endWord, wordList, new HashSet<>(wordList.size()));
//            return result == Integer.MAX_VALUE ? 0 : result + 2;
//        }
//
//        private int ladderLength(String beginWord, String endWord, List<String> wordList, Set<String> used) {
//            int len = Integer.MAX_VALUE;
//            if (used.size() == wordList.size()) {
//                return len;
//            }
//            Set<String> beginSet = new HashSet<>();
//            for (String targetWord : wordList) {
//                if (!used.contains(targetWord) && similarWords(beginWord, targetWord)) {
//                    if (targetWord.equals(endWord)){
//                        return 0;
//                    }else {
//                        beginSet.add(targetWord);
//                    }
//                }
//            }
//            for (String targetWord:beginSet){
//                used.add(targetWord);
//                int result = ladderLength(targetWord, endWord, wordList, used);
//                len = Math.min(len, result);
//                used.remove(targetWord);
//            }
//
//            return len != Integer.MAX_VALUE ? len + 1 : Integer.MAX_VALUE;
//        }
//
//        private boolean similarWords(String world1, String world2) {
//            if (world1.length() != world2.length()) {
//                return false;
//            }
//            boolean similar = true;
//            for (int index = 0; index < world1.length(); index++) {
//                if (world1.charAt(index) != world2.charAt(index)) {
//                    if (similar) {
//                        similar = false;
//                    } else {
//                        return false;
//                    }
//                }
//            }
//            return true;
//        }
//    }
//leetcode submit region end(Prohibit modification and deletion)

}