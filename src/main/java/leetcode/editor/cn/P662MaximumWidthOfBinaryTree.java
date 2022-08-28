//给你一棵二叉树的根节点 root ，返回树的 最大宽度 。 
//
// 树的 最大宽度 是所有层中最大的 宽度 。 
//
// 
// 
// 每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 
//null 节点，这些 null 节点也计入长度。 
//
// 题目数据保证答案将会在 32 位 带符号整数范围内。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,3,2,5,3,null,9]
//输出：4
//解释：最大宽度出现在树的第 3 层，宽度为 4 (5,3,null,9) 。
// 
//
// 示例 2： 
//
// 
//输入：root = [1,3,2,5,null,null,9,6,null,7]
//输出：7
//解释：最大宽度出现在树的第 4 层，宽度为 7 (6,null,null,null,null,null,7) 。
// 
//
// 示例 3： 
//
// 
//输入：root = [1,3,2,5]
//输出：2
//解释：最大宽度出现在树的第 2 层，宽度为 2 (3,2) 。
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数目范围是 [1, 3000] 
// -100 <= Node.val <= 100 
// 
// 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 464 👎 0

package leetcode.editor.cn;

import javafx.util.Pair;

import java.util.*;

public class P662MaximumWidthOfBinaryTree {
    public static void main(String[] args) {
        Solution solution = new P662MaximumWidthOfBinaryTree().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        /**
         * dfs
         *
         * @param root
         * @return
         */
        public int widthOfBinaryTree(TreeNode root) {
            Map<Integer, Integer> mapL = new HashMap<>();
            return dfs(root, mapL, 1, 1) + 1;
        }


        private int dfs(TreeNode root, Map<Integer, Integer> leftMap, int level, int num) {
            if (Objects.isNull(root)) {
                return -1;
            }
            leftMap.putIfAbsent(level, num);//优先左节点遍历不可能出现更小的情况
            return Math.max(num - leftMap.get(level), Math.max(
                    dfs(root.left, leftMap, level + 1, num * 2)
                    , dfs(root.right, leftMap, level + 1, num * 2 + 1)));
        }

        /**
         * bfs
         *
         * @param root
         * @return
         */
        public int widthOfBinaryTree1(TreeNode root) {
            LinkedList<Pair<TreeNode, Integer>> cache = new LinkedList<>();
            cache.addFirst(new Pair<>(root, 1));
            int result = 0;
            while (!cache.isEmpty()) {
                int size = cache.size();
                result = Math.max(result, cache.peekFirst().getValue() - cache.peekLast().getValue());
                for (int index = 0; index < size; index++) {
                    Pair<TreeNode, Integer> treeNodeIntegerPair = cache.removeLast();
                    TreeNode treeNode = treeNodeIntegerPair.getKey();
                    Integer value = treeNodeIntegerPair.getValue();
                    if (Objects.nonNull(treeNode.left)) {
                        cache.addFirst(new Pair<>(treeNode.left, value * 2));
                    }
                    if (Objects.nonNull(treeNode.right)) {
                        cache.addFirst(new Pair<>(treeNode.right, value * 2 + 1));
                    }
                }
            }
            return result + 1;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}