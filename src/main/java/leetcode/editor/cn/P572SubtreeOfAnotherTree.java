//
// 
// 给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true ；否则
//，返回 false 。 
//
// 二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,4,5,1,2], subRoot = [4,1,2]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// root 树上的节点数量范围是 [1, 2000] 
// subRoot 树上的节点数量范围是 [1, 1000] 
// -10⁴ <= root.val <= 10⁴ 
// -10⁴ <= subRoot.val <= 10⁴ 
// 
// 
// 
// Related Topics 树 深度优先搜索 二叉树 字符串匹配 哈希函数 👍 753 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P572SubtreeOfAnotherTree {
    public static void main(String[] args) {
        Solution solution = new P572SubtreeOfAnotherTree().new Solution();
        int[] ints = solution.buildJumpArray(Arrays.asList("a", "b", "a", "c", "a", "b", "a", "b"));
        System.out.println(Arrays.toString(ints));
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
        private String NULL = "n";

        /**
         * kmp
         */
        public boolean isSubtree(TreeNode root, TreeNode subRoot) {
            List<String> rootList = new ArrayList<>();
            treeNodeToList(root, rootList);
            System.out.println(rootList);
            List<String> subList = new ArrayList<>();
            treeNodeToList(subRoot, subList);
            System.out.println(subList);
            return kmp(rootList, subList);
        }

        private void treeNodeToList(TreeNode node, List<String> list) {
            if (Objects.isNull(node)) {
                list.add(NULL);
                return;
            }
            list.add(String.valueOf(node.val));
            treeNodeToList(node.left, list);
            treeNodeToList(node.right, list);
        }

        private boolean kmp(List<String> rootList, List<String> subList) {
            int[] jumpArray = buildJumpArray(subList);
//            System.out.println(Arrays.toString(jumpArray));
            int rootIndex = 0;
            int subIndex = 0;
            while (rootIndex < rootList.size()) {
                if (rootList.get(rootIndex).equals(subList.get(subIndex))) {
                    //比较相等后双指针后移动
                    rootIndex++;
                    subIndex++;
                } else {
                    if (subIndex > 0) {
                        //尝试找到 subList 中有复用的元素链
                        subIndex = jumpArray[subIndex - 1];
                    } else {
                        //subIndex 已经是第一个元素了，所以subList没有重复的
                        rootIndex++;
                    }
                }
                if (subIndex == subList.size()) {
                    return true;
                }
            }
            return false;
        }

        public int[] buildJumpArray(List<String> subList) {
            int[] result = new int[subList.size()];
            int todoIndex = 1;
//            int prevIndex = 0;
            while (todoIndex < subList.size()) {
                int lastIndex = result[todoIndex - 1];
                String todoNumber = subList.get(todoIndex);
                while (lastIndex!=0 && !todoNumber.equals(subList.get(lastIndex))){
                    lastIndex = result[lastIndex-1];
                }
                if (todoNumber.equals(subList.get(lastIndex))){
                    result[todoIndex] = lastIndex+1;
                }else {
                    result[todoIndex] = 0;
                }
                todoIndex++;
            }
            return result;
        }


        /**
         * 暴力求解
         *
         * @param root
         * @param subRoot
         * @return
         */
        public boolean isSubtree1(TreeNode root, TreeNode subRoot) {
            if (compare(root, subRoot)) {
                return true;
            }
            if (root.left != null && isSubtree(root.left, subRoot)) {
                return true;
            }
            if (root.right != null && isSubtree(root.right, subRoot)) {
                return true;
            }
            return false;
        }

        private boolean compare(TreeNode root, TreeNode subRoot) {
            if (Objects.isNull(subRoot) && Objects.nonNull(root)) {
                return false;
            }
            if (Objects.isNull(root) && Objects.nonNull(subRoot)) {
                return false;
            }
            if (Objects.isNull(root)) {
                return true;
            }
            if (root.val != subRoot.val) {
                return false;
            }
            return compare(root.left, subRoot.left) && compare(root.right, subRoot.right);
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