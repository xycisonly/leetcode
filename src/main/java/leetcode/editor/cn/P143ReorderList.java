//给定一个单链表 L 的头节点 head ，单链表 L 表示为： 
//
// 
//L0 → L1 → … → Ln - 1 → Ln
// 
//
// 请将其重新排列后变为： 
//
// 
//L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → … 
//
// 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：head = [1,2,3,4]
//输出：[1,4,2,3] 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [1,2,3,4,5]
//输出：[1,5,2,4,3] 
//
// 
//
// 提示： 
//
// 
// 链表的长度范围为 [1, 5 * 10⁴] 
// 1 <= node.val <= 1000 
// 
// Related Topics 栈 递归 链表 双指针 👍 955 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P143ReorderList {
    public static void main(String[] args) {
        Solution solution = new P143ReorderList().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public void reorderList(ListNode head) {
            if (Objects.isNull(head) || Objects.isNull(head.next)) {
                return;
            }
            //双指针找中间节点
            ListNode oneIndex = head;
            ListNode twoIndex = head;
            while (Objects.nonNull(twoIndex.next)&&Objects.nonNull(twoIndex.next.next)) {
                oneIndex = oneIndex.next;
                twoIndex = twoIndex.next.next;
            }
            if (Objects.nonNull(twoIndex.next)) {
                twoIndex = twoIndex.next;
            }

            //中间节点之后节点换位置
            ListNode prev = oneIndex;
            oneIndex = oneIndex.next;
            prev.next = null;
            prev = null;
            while (Objects.nonNull(oneIndex)) {
                ListNode next = oneIndex.next;
                oneIndex.next = prev;
                prev = oneIndex;
                oneIndex = next;
            }
            //构建
            ListNode result = new ListNode();
            while (Objects.nonNull(head)) {
                result.next = head;
                result = head;
                head = head.next;
                if (Objects.nonNull(twoIndex)) {
                    result.next = twoIndex;
                    result = twoIndex;
                    twoIndex = twoIndex.next;
                }
            }
            result.next = null;
        }

        /**
         * 使用LinkedList进行存储
         *
         * @param head
         */
        public void reorderList1(ListNode head) {
            if (Objects.isNull(head)) {
                return;
            }
            LinkedList<ListNode> cache = new LinkedList<>();
            while (Objects.nonNull(head)) {
                cache.addFirst(head);
                head = head.next;
            }
            ListNode prev = cache.removeLast();
            boolean first = true;
            while (!cache.isEmpty()) {
                if (first) {
                    prev.next = cache.removeFirst();
                } else {
                    prev.next = cache.removeLast();
                }
                first = !first;
                prev = prev.next;
            }
            prev.next = null;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}