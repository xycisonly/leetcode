//给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节
//点值之和。 
//
// 
//
// 例如： 
//
// 输入: 原始二叉搜索树:
//              5
//            /   \
//           2     13
//
//输出: 转换为累加树:
//             18
//            /   \
//          20     13
// 
//
// 
//
// 注意：本题和 1038: https://leetcode-cn.com/problems/binary-search-tree-to-greater-s
//um-tree/ 相同 
// Related Topics 树 
// 👍 298 👎 0

package leetcode.editor.cn;

import leetcode.editor.cn.node.TreeNode;

import java.util.*;
  public class P538ConvertBstToGreaterTree{
      public static void main(String[] args) {
           Solution solution = new P538ConvertBstToGreaterTree().new Solution();
          TreeNode root = new TreeNode(2);
          TreeNode left = new TreeNode(0);
          TreeNode ll = new TreeNode(-4);
          TreeNode lr = new TreeNode(1);
          root.left=left;root.right = new TreeNode(3);
          left.left = ll;left.right=lr;
          solution.convertBST(root);
          System.out.println(root);
      }
        //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode convertBST(TreeNode root) {
        convertBST(0,root);
        return root;
    }

    public int convertBST(int value, TreeNode root){
        if (null==root){
            return value;
        }
        int rightValue = convertBST(value, root.right);
        root.val += rightValue;
        return convertBST(root.val,root.left);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

  }