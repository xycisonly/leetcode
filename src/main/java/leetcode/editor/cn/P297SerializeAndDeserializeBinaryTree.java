//序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方
//式重构得到原数据。 
//
// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串
//反序列化为原始的树结构。 
//
// 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的
//方法解决这个问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,3,null,null,4,5]
//输出：[1,2,3,null,null,4,5]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 树中结点数在范围 [0, 10⁴] 内 
// -1000 <= Node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 👍 895 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P297SerializeAndDeserializeBinaryTree {
    public static void main(String[] args) {
//        Solution solution = new P297SerializeAndDeserializeBinaryTree().new Solution();
        Codec codec = new P297SerializeAndDeserializeBinaryTree().new Codec();
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(4);
        node.left.left = new TreeNode(3);
        String serialize = codec.serialize(node);
        System.out.println(serialize);
        TreeNode deserialize = codec.deserialize(serialize);
        System.out.println(deserialize);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Codec {
        public final Character NULL = 'N';
        public static final String LEFT = "(";
        public static final String RIGHT = ")";

        public String serialize(TreeNode root) {
            if (Objects.isNull(root)) {
                return "";
            }
            StringBuilder result = new StringBuilder();
            nodeToString(root, result);
            return result.toString();
        }

        private void nodeToString(TreeNode root, StringBuilder sb) {
            if (Objects.isNull(root)) {
                sb.append(NULL);
                return;
            }
            sb.append(LEFT);
            nodeToString(root.left, sb);
            sb.append(root.val);
            nodeToString(root.right, sb);
            sb.append(RIGHT);
        }

        public TreeNode deserialize(String data) {
            if (data.length() == 0) {
                return null;
            }
            return parse(data, 0).treeNode;
        }

        private Result parse(String data, int index) {
            if (NULL.equals(data.charAt(index))) {
                return new Result(index + 1, null);
            }
            Result left = parse(data, index + 1);
            int valueEnd = getVal(data, left.index);
            int value = Integer.parseInt(data.substring(left.index, valueEnd));
            Result right = parse(data, valueEnd);
            TreeNode treeNode = new TreeNode(value);
            treeNode.left = left.treeNode;
            treeNode.right = right.treeNode;
            return new Result(right.index + 1, treeNode);
        }

        private int getVal(String data, int index) {
            char aChar;
            while (Character.isDigit(aChar = data.charAt(index))||'-' == aChar){
                index++;
            }
            return index;
        }

        public class Result {
            //已经用到了哪个

            public int index;
            public TreeNode treeNode;

            Result() {
            }

            public Result(int index, TreeNode treeNode) {
                this.treeNode = treeNode;
                this.index = index;
            }
        }
    }

    /**
     * 先序遍历
     * 1,2,N,N,N
     */
//    public class Codec1 {
//        public static final String NULL = "N";
//        public static final String SPLIT = ",";
//
//        // Encodes a tree to a single string.
//        public String serialize(TreeNode root) {
//            StringBuilder serialize = new StringBuilder();
//            buildStr(serialize, root);
//            return serialize.substring(0, serialize.length() - 1);
//        }
//
//        private void buildStr(StringBuilder stringBuilder, TreeNode node) {
//            if (Objects.isNull(node)) {
//                stringBuilder.append(NULL).append(SPLIT);
//                return;
//            }
//            stringBuilder.append(node.val).append(SPLIT);
//            buildStr(stringBuilder, node.left);
//            buildStr(stringBuilder, node.right);
//        }
//
//        // Decodes your encoded data to tree.
//        public TreeNode deserialize(String data) {
//            return buildTreeNode(data.split(SPLIT), 0).treeNode;
//        }
//
//        private Result buildTreeNode(String[] data, int index) {
//            if (index == data.length || NULL.equals(data[index])) {
//                return new Result(index, null);
//            }
//
//            TreeNode treeNode = new TreeNode(Integer.parseInt(data[index]));
//            Result result = buildTreeNode(data, index + 1);
//            treeNode.left = result.treeNode;
//            Result result2 = buildTreeNode(data, result.index + 1);
//            treeNode.right = result2.treeNode;
//            return new Result(result2.index, treeNode);
//        }
//
//        public class Result {
//            //已经用到了哪个
//            public int index;
//            public TreeNode treeNode;
//
//            Result() {
//            }
//
//            public Result(int index, TreeNode treeNode) {
//                this.treeNode = treeNode;
//                this.index = index;
//            }
//        }
//    }

    // Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}