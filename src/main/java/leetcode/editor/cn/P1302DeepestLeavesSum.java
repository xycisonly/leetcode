//给你一棵二叉树的根节点 root ，请你返回 层数最深的叶子节点的和 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
//输出：15
// 
//
// 示例 2： 
//
// 
//输入：root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
//输出：19
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [1, 10⁴] 之间。 
// 1 <= Node.val <= 100 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 105 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P1302DeepestLeavesSum {
    public static void main(String[] args) {
        Solution solution = new P1302DeepestLeavesSum().new Solution();
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
        int max = 0;
        int maxLevel = 0;
        public int deepestLeavesSum(TreeNode root) {
            dfs(root,1);
            return max;
        }
        private void dfs(TreeNode root,int level){
            if (Objects.isNull(root)){
                return;
            }
            if (level>maxLevel){
                maxLevel = level;
                max = root.val;
            }else if (level == maxLevel){
                max += root.val;
            }
            dfs(root.left,level+1);
            dfs(root.right,level+1);
        }

        /**
         * 广度遍历
         * @param root
         * @return
         */
        public int deepestLeavesSum1(TreeNode root) {
            int max = 0;
//            if (Objects.isNull(root)){
//                return max;
//            }
            LinkedList<TreeNode> cache = new LinkedList<>();
            cache.add(root);
            while (!cache.isEmpty()){
                int size = cache.size();
                max = 0;
                for (int index = 0;index < size;index++){
                    TreeNode treeNode = cache.removeLast();
                    max+= treeNode.val;
                    if (Objects.nonNull(treeNode.left)){
                        cache.addFirst(treeNode.left);
                    }
                    if (Objects.nonNull(treeNode.right)){
                        cache.addFirst(treeNode.right);
                    }
                }
            }
            return max;
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