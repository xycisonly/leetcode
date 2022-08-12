//给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: [1,2,3,null,5,null,4]
//输出: [1,3,4]
// 
//
// 示例 2: 
//
// 
//输入: [1,null,3]
//输出: [1,3]
// 
//
// 示例 3: 
//
// 
//输入: []
//输出: []
// 
//
// 
//
// 提示: 
//
// 
// 二叉树的节点个数的范围是 [0,100] 
// -100 <= Node.val <= 100 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 733 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P199BinaryTreeRightSideView {
    public static void main(String[] args) {
        Solution solution = new P199BinaryTreeRightSideView().new Solution();
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
        int maxDeep = 0;

        /**
         * @param root
         * @return
         */
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> result = new LinkedList<>();
            if (Objects.isNull(root)){
                return result;
            }
            LinkedList<TreeNode> cache = new LinkedList<>();
            cache.addFirst(root);

            while (!cache.isEmpty()) {
                int size = cache.size();
                for (int number = 0; number < size; number++) {
                    TreeNode treeNode = cache.removeLast();
                    if (number == 0) {
                        result.add(treeNode.val);
                    }
                    if (Objects.nonNull(treeNode.right)) {
                        cache.addFirst(treeNode.right);
                    }
                    if (Objects.nonNull(treeNode.left)) {
                        cache.addFirst(treeNode.left);
                    }
                }
            }
            return result;
        }

        /**
         * 深度遍历
         *
         * @param root
         * @return
         */
        public List<Integer> rightSideView1(TreeNode root) {
            List<Integer> result = new LinkedList<>();
            dfs(result, root, 1);
            return result;
        }

        private void dfs(List<Integer> result, TreeNode root, int deep) {
            if (Objects.isNull(root)) {
                return;
            }
            if (deep > maxDeep) {
                maxDeep = deep;
                result.add(root.val);
            }
            dfs(result, root.right, deep + 1);
            dfs(result, root.left, deep + 1);
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