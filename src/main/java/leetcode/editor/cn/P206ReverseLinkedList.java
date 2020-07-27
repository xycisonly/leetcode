//反转一个单链表。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 进阶: 
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ 
// Related Topics 链表 
// 👍 1108 👎 0

package leetcode.editor.cn;

import leetcode.editor.cn.node.ListNode;

import java.util.*;

public class P206ReverseLinkedList {
    public static void main(String[] args) {
        Solution solution = new P206ReverseLinkedList().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public ListNode reverseList(ListNode head) {
            ListNode last = null;
            while (head!=null){
                ListNode next2 = head.next;
                head.next = last;
                last = head;
                head = next2;
            }
            return last;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}