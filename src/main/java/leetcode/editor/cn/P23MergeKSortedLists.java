//给你一个链表数组，每个链表都已经按升序排列。 
//
// 请你将所有链表合并到一个升序链表中，返回合并后的链表。 
//
// 
//
// 示例 1： 
//
// 输入：lists = [[1,4,5],[1,3,4],[2,6]]
//输出：[1,1,2,3,4,4,5,6]
//解释：链表数组如下：
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//将它们合并到一个有序链表中得到。
//1->1->2->3->4->4->5->6
// 
//
// 示例 2： 
//
// 输入：lists = []
//输出：[]
// 
//
// 示例 3： 
//
// 输入：lists = [[]]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// k == lists.length 
// 0 <= k <= 10^4 
// 0 <= lists[i].length <= 500 
// -10^4 <= lists[i][j] <= 10^4 
// lists[i] 按 升序 排列 
// lists[i].length 的总和不超过 10^4 
// 
// Related Topics 链表 分治 堆（优先队列） 归并排序 
// 👍 1992 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P23MergeKSortedLists {
    public static void main(String[] args) {
        Solution solution = new P23MergeKSortedLists().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)


    class Solution {
        /**
         * 分治思想两两合并
         *
         * @param lists
         * @return
         */
        public ListNode mergeKLists(ListNode[] lists) {
            if (Objects.isNull(lists) || lists.length < 1) {
                return null;
            }
            int len = lists.length;
            while (len > 1) {
                int weight = len / 2;
                boolean remain = len % 2 == 1;
                for (int index = 0; index < weight; index++) {
                    lists[index] = merge(lists[index], lists[index + weight]);
                }
                if (remain) {
                    lists[weight] = lists[len - 1];
                }
                len = remain ? weight + 1 : weight;
            }
            return lists[0];
        }

        private ListNode merge(ListNode node1, ListNode node2) {
            ListNode head = new ListNode();
            ListNode tail = head;
            while (node1 != null && node2 != null) {
                if (node1.val <= node2.val) {
                    tail.next = node1;
                    tail = node1;
                    node1 = node1.next;
                } else {
                    tail.next = node2;
                    tail = node2;
                    node2 = node2.next;
                }
            }
            if (node1 == null) {
                tail.next = node2;
            } else {
                tail.next = node1;
            }
            return head.next;
        }

        /**
         * 从所有链表中每次取得最小的node
         *
         * @param lists list
         * @return linked
         */
        public ListNode mergeKLists1(ListNode[] lists) {
            //使用优先级队列，每次拿到所有lists 中最小的节点
            PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparing(node -> node.val));
            for (ListNode node : lists) {
                if (Objects.nonNull(node)) {
                    queue.offer(node);
                }
            }
            ListNode head = new ListNode();
            ListNode tail = head;
            while (!queue.isEmpty()) {
                ListNode poll = queue.poll();
                tail.next = poll;
                tail = poll;
                //如果还有下一个node 放入优先级队列进行下一次排队
                if (Objects.nonNull(poll.next)) {
                    queue.offer(poll.next);
                    poll.next = null;
                }
            }
            return head.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

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
