//给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并
//返回其根节点。 
//
// 
//
// 示例 1: 
//
// 
//输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//输出: [3,9,20,null,null,15,7]
// 
//
// 示例 2: 
//
// 
//输入: preorder = [-1], inorder = [-1]
//输出: [-1]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= preorder.length <= 3000 
// inorder.length == preorder.length 
// -3000 <= preorder[i], inorder[i] <= 3000 
// preorder 和 inorder 均 无重复 元素 
// inorder 均出现在 preorder 
// preorder 保证 为二叉树的前序遍历序列 
// inorder 保证 为二叉树的中序遍历序列 
// 
// Related Topics 树 数组 哈希表 分治 二叉树 👍 1659 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P105ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
        Solution solution = new P105ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
        System.out.println("asdfoi".hashCode());
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
        int preorderIndex = 0;
        Map<Integer, Integer> inorderValueIndexMap = new HashMap<>();

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (Objects.isNull(preorder) || preorder.length == 0) {
                return null;
            }
            TreeNode root = new TreeNode(preorder[0]);
            LinkedList<TreeNode> cache = new LinkedList<>();
            cache.addFirst(root);
            int inorderIndex = 0;
            for (int preorderIndex = 1; preorderIndex < preorder.length; preorderIndex++) {
                TreeNode treeNode = new TreeNode(preorder[preorderIndex]);
                TreeNode peekNode = cache.peekFirst();
                if (peekNode.val == inorder[inorderIndex]) {
                    while (!cache.isEmpty() && cache.peekFirst().val == inorder[inorderIndex]) {
                        peekNode = cache.removeFirst();
                        inorderIndex++;
                    }

                    peekNode.right = treeNode;
                    cache.addFirst(treeNode);
                } else {
                    peekNode.left = treeNode;
                    cache.addFirst(treeNode);
                }
            }
            return root;
        }

        /**
         * 迭代法
         *
         * @param preorder
         * @param inorder
         * @return
         */
        public TreeNode buildTree1(int[] preorder, int[] inorder) {
            for (int index = 0; index < inorder.length; index++) {
                inorderValueIndexMap.put(inorder[index], index);
            }
            return build(preorder, inorder, 0, inorder.length - 1);
        }

        public TreeNode build(int[] preorder, int[] inorder, int inorderBegin, int inorderEnd) {
            if (preorderIndex == preorder.length || inorderBegin > inorderEnd) {
                return null;
            }
            int nodeVal = preorder[preorderIndex];
            //find
            Integer inorderIndex = inorderValueIndexMap.get(nodeVal);
            if (inorderIndex >= inorderBegin && inorderIndex <= inorderEnd) {
                preorderIndex++;
                TreeNode left = build(preorder, inorder, inorderBegin, inorderIndex - 1);
                TreeNode right = build(preorder, inorder, inorderIndex + 1, inorderEnd);
                return new TreeNode(nodeVal, left, right);
            } else {
                return null;
            }

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