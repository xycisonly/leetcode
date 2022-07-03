//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
// Related Topics 字符串 动态规划 回溯 👍 2724 👎 0

package leetcode.editor.cn;

import java.util.*;
import java.util.stream.Collectors;

public class P22GenerateParentheses {
    public static void main(String[] args) {
        Solution solution = new P22GenerateParentheses().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private static final String LEFT = "(";
        private static final String RIGHT = ")";

        /**
         * 拆分生成的()递归求解
         * @param n
         * @return
         */
        public List<String> generateParenthesis3(int n) {
            List<String>[] cache = new List[n+1];
            cache[0] = Collections.singletonList("");
            return doGenerateParenthesis(n,cache);
        }

        private List<String> doGenerateParenthesis(int n, List<String>[] cache) {
            if (Objects.nonNull(cache[n])) {
                return cache[n];
            }
            List<String> result = new ArrayList<>();
            cache[n] = result;
            for (int a = 0; a < n; a++) {
                for (String aKey:doGenerateParenthesis(a,cache)){
                    for (String bKey:doGenerateParenthesis(n-a-1,cache)){
                        result.add(LEFT+aKey+RIGHT+bKey);
                    }
                }
            }
            return result;
        }

        /**
         * 深优先回溯
         *
         * @param n
         * @return
         */
        public List<String> generateParenthesis2(int n) {
            ArrayList<String> result = new ArrayList<>();
            deepBuild(new StringBuilder(LEFT), n, 1, 0, result);
            return result;
        }

        public void deepBuild(StringBuilder str, int n, int lSize, int rSize, List<String> result) {
            if (str.length() == 2 * n) {
                result.add(str.toString());
                return;
            }
            if (lSize < n) {
                deepBuild(str.append(LEFT), n, lSize + 1, rSize, result);
                str.deleteCharAt(str.length() - 1);
            }
            if (rSize < n && rSize < lSize) {
                deepBuild(str.append(RIGHT), n, lSize, rSize + 1, result);
                str.deleteCharAt(str.length() - 1);
            }
        }

        /**
         * 光优先，速度较低
         *
         * @param n
         * @return
         */
        public List<String> generateParenthesis1(int n) {
            LinkedList<Node> resultCache = new LinkedList<>();
            resultCache.addFirst(new Node(LEFT, 1, 0));
            for (int number = 1; number < n * 2; number++) {
                int size = resultCache.size();
                for (int innerNumber = 0; innerNumber < size; innerNumber++) {
                    Node node = resultCache.removeLast();
                    if (node.rightSize < n && node.rightSize < node.leftSize) {
                        resultCache.addFirst(new Node(node.value + RIGHT, node.leftSize, node.rightSize + 1));
                    }
                    if (node.leftSize < n) {
                        resultCache.addFirst(new Node(node.value + LEFT, node.leftSize + 1, node.rightSize));
                    }
                }
            }
            return resultCache.stream().map(Node::getValue).collect(Collectors.toList());
        }
    }

    class Node {
        public String value;
        public int leftSize;
        public int rightSize;

        public Node(String value, int leftSize, int rightSize) {
            this.leftSize = leftSize;
            this.rightSize = rightSize;
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}