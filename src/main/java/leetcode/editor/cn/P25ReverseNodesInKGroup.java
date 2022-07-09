//给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。 
//
// k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], k = 2
//输出：[2,1,4,3,5]
// 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [1,2,3,4,5], k = 3
//输出：[3,2,1,4,5]
// 
//
// 
//提示：
//
// 
// 链表中的节点数目为 n 
// 1 <= k <= n <= 5000 
// 0 <= Node.val <= 1000 
// 
//
// 
//
// 进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？ 
//
// 
// 
// Related Topics 递归 链表 👍 1701 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P25ReverseNodesInKGroup {

    public static void main(String[] args) {
        Solution solution = new P25ReverseNodesInKGroup().new Solution();
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
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode hair = new ListNode(0);
            hair.next = head;
            ListNode pre = hair;

            while (head != null) {
                ListNode tail = pre;
                // 查看剩余部分长度是否大于等于 k
                for (int i = 0; i < k; ++i) {
                    tail = tail.next;
                    if (tail == null) {
                        return hair.next;
                    }
                }
                ListNode nex = tail.next;
                ListNode[] reverse = myReverse(head, tail);
                head = reverse[0];
                tail = reverse[1];
                // 把子链表重新接回原链表
                pre.next = head;
                tail.next = nex;
                pre = tail;
                head = tail.next;
            }

            return hair.next;
        }

        public ListNode[] myReverse(ListNode head, ListNode tail) {
            ListNode prev = tail.next;
            ListNode p = head;
            while (prev != tail) {
                ListNode nex = p.next;
                p.next = prev;
                prev = p;
                p = nex;
            }
            return new ListNode[]{tail, head};
        }

        /**
         *
         * @param head
         * @param k
         * @return
         */
        public ListNode reverseKGroup2(ListNode head, int k) {
            ListNode result = new ListNode();
            result.next = head;
            ListNode prevList = result;
            ListNode first = null;
            ListNode prev = result;
            int number = 0;
            while (Objects.nonNull(head)){
                if (Objects.isNull(first)){
                    first = head;
                    if (!find(head,k)){
                        break;
                    }
                }
                ListNode next = head.next;
                head.next = prev;
                prev = head;
                head = next;
                number++;
                if (number==k){
                    prevList.next = prev;
                    prevList = first;
                    prevList.next = head;
                    first = null;
                    number=0;
                }
            }
            return result.next;
        }
        private boolean find(ListNode head, int k){
            while (Objects.nonNull(head)){
                k--;
                head = head.next;
                if (k==0){
                    return true;
                }
            }
            return false;
        }
        /**
         * 使用栈缓存
         * @param head
         * @param k
         * @return
         */
        public ListNode reverseKGroup1(ListNode head, int k) {
            ListNode result = new ListNode();
            result.next = head;
            ListNode prev = result;
            LinkedList<ListNode> cache = new LinkedList<>();
            while (Objects.nonNull(head)) {
                cache.addFirst(head);
                head = head.next;
                if (cache.size() == k) {
                    while (cache.size() != 0) {
                        ListNode listNode = cache.removeFirst();
                        prev.next = listNode;
                        prev = listNode;
                    }
                    prev.next = head;
                }
            }
            return result.next;
        }

        private void toString(ListNode listNode){
            StringBuilder sb = new StringBuilder();
            while (listNode!=null){
                sb.append("#").append(listNode.val);
                listNode = listNode.next;
            }
            System.out.println(sb);
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    public class ListNode {
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

        @Override
        public String toString() {
            return "#"+val+(Objects.nonNull(next)?next.toString():"");
        }
    }
}