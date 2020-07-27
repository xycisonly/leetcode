//请判断一个链表是否为回文链表。 
//
// 示例 1: 
//
// 输入: 1->2
//输出: false 
//
// 示例 2: 
//
// 输入: 1->2->2->1
//输出: true
// 
//
// 进阶： 
//你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
// Related Topics 链表 双指针 
// 👍 572 👎 0

package leetcode.editor.cn;

import leetcode.editor.cn.node.ListNode;

import java.util.*;
  public class P234PalindromeLinkedList{
      public static void main(String[] args) {
           Solution solution = new P234PalindromeLinkedList().new Solution();
      }
        //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

class Solution {

    public boolean isPalindrome(ListNode head) {
        if (head == null){
            return true;
        }
        //找到中心节点
        ListNode slow = head;
        ListNode fast = head;
        while (null != fast.next){
            if (null == fast.next.next){
                slow = slow.next;
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        //反转后半截点
        ListNode slowNext = slow.next;
        slow.next = null;
        while (slowNext!=null){
            ListNode slowNextNext = slowNext.next;
            slowNext.next = slow;
            slow = slowNext;
            slowNext = slowNextNext;
        }
        //判断是否是回文字
        while (slow!=null){
            if (head.val!=slow.val) {
                return false;
            }
            head = head.next;
            slow = slow.next;
        }
        return true;
    }
}
class Solution1{
    private ListNode compareNode;
    public boolean isPalindrome(ListNode head) {
        //时间 On 空间On。转数组不写了。还有递归的方式。也是双On。
        compareNode = head;
        return compare(head);
    }
    private boolean compare(ListNode node){
        if (null == node){
            return true;
        }
        if (!compare(node.next)){
            return false;
        }
        if (compareNode.val != node.val){
            return false;
        }
        compareNode = compareNode.next;
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

  }