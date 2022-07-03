//给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：[[3],[20,9],[15,7]]
// 
//
// 示例 2： 
//
// 
//输入：root = [1]
//输出：[[1]]
// 
//
// 示例 3： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 2000] 内 
// -100 <= Node.val <= 100 
// 
// Related Topics 树 广度优先搜索 二叉树 👍 652 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P103BinaryTreeZigzagLevelOrderTraversal {
    public static void main(String[] args) {
        Solution solution = new P103BinaryTreeZigzagLevelOrderTraversal().new Solution();
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
    public class Solution {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            if (Objects.isNull(root)){
                return new ArrayList<>();
            }
            LinkedList<TreeNode> todoList = new LinkedList<>();
            List<List<Integer>> result = new LinkedList<>();
            todoList.add(root);
            boolean left = true;
            while (!todoList.isEmpty()) {
                int size = todoList.size();
                List<Integer> resultSonList = new LinkedList<>();
                result.add(resultSonList);
                for (int index = 0; index < size; index++) {
                    if (left) {
                        TreeNode treeNode = todoList.removeFirst();
                        resultSonList.add(treeNode.val);
                        add(treeNode.left, todoList, true);
                        add(treeNode.right, todoList, true);

                    } else {
                        TreeNode treeNode = todoList.removeLast();
                        resultSonList.add(treeNode.val);
                        add(treeNode.right, todoList, false);
                        add(treeNode.left, todoList, false);
                    }

                }
                left = !left;
            }
            return result;
        }

        private void add(TreeNode node, LinkedList<TreeNode> todoList, boolean left) {
            if (Objects.nonNull(node)) {
                if (left) {
                    todoList.addLast(node);
                } else {
                    todoList.addFirst(node);
                }
            }
        }


    }
//leetcode submit region end(Prohibit modification and deletion)
class TreeNode {
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