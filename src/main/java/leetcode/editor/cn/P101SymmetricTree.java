//给定一个二叉树，检查它是否是镜像对称的。 
//
// 
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。 
//
//     1
//   / \
//  2   2
// / \ / \
//3  4 4  3
// 
//
// 
//
// 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的: 
//
//     1
//   / \
//  2   2
//   \   \
//   3    3
// 
//
// 
//
// 进阶： 
//
// 你可以运用递归和迭代两种方法解决这个问题吗？ 
// Related Topics 树 深度优先搜索 广度优先搜索 
// 👍 911 👎 0

package leetcode.editor.cn;

import leetcode.editor.cn.node.TreeNode;

import java.util.*;

public class P101SymmetricTree {
    public static void main(String[] args) {
        Solution solution = new P101SymmetricTree().new Solution();
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(2);
//        treeNode.left.left = new TreeNode(3);
        treeNode.left.right = new TreeNode(4);
        treeNode.right.left = new TreeNode(4);
//        treeNode.right.right = new TreeNode(3);
        System.out.println(solution.isSymmetric(treeNode));
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        //迭代

        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            TreeNode left;
            TreeNode right;
            queue.offer(root.left);
            queue.offer(root.right);
            while (queue.size() > 1) {
                left = queue.poll();
                right = queue.poll();
                if (null == left && null == right) {
                    continue;
                }
                if (null == left || null == right) {
                    return false;
                }
                if (left.val != right.val) {
                    return false;
                }
                queue.offer(left.left);
                queue.offer(right.right);
                queue.offer(left.right);
                queue.offer(right.left);
            }
            return true;
        }
    }

    class Solution1 {
        //递归
        public boolean isSymmetric(TreeNode root) {
            if (null == root) {
                return true;
            }
            return check(root.left, root.right);
        }

        public boolean check(TreeNode left, TreeNode right) {
            if (null == left && null == right) {
                return true;
            }
            if (null == left || null == right) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }
            return check(left.left, right.right) && check(left.right, right.left);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}