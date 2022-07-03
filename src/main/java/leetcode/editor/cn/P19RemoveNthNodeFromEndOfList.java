//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1], n = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2], n = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中结点的数目为 sz 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
//
// 
//
// 进阶：你能尝试使用一趟扫描实现吗？ 
// Related Topics 链表 双指针 👍 2102 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P19RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        Solution solution = new P19RemoveNthNodeFromEndOfList().new Solution();
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
        /**
         * 双指针
         * @param head
         * @param n
         * @return
         */
        public ListNode removeNthFromEnd(ListNode head, int n){
            ListNode hf = new ListNode(0);hf.next = head;
            ListNode currentNode = hf;
            for (int index = 0;index<n;index++){
                currentNode = currentNode.next;
            }
            ListNode prev = hf;
            while (true){
                if (Objects.isNull(currentNode.next)){
                    prev.next = prev.next.next;
                    break;
                }
                currentNode = currentNode.next;
                prev = prev.next;
            }
            return hf.next;
        }

        /**
         * 栈
         * @param head
         * @param n
         * @return
         */
        public ListNode removeNthFromEnd1(ListNode head, int n) {
            ListNode hf = new ListNode(0);hf.next = head;
            LinkedList<ListNode> cache = new LinkedList<>();
            ListNode current = hf;
            while (Objects.nonNull(current)){
                cache.addLast(current);
                current = current.next;
            }
            int number = 1;
            ListNode prev = null;
            while (true){
                current = cache.removeLast();
                if (n==number){
                    cache.removeLast().next = prev;
                    break;
                }else {
                    prev=current;
                }
                number++;
            }
            return hf.next;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class ListNode {
        int val;
        ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }
}