//中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。 
//
// 例如， 
//
// [2,3,4] 的中位数是 3 
//
// [2,3] 的中位数是 (2 + 3) / 2 = 2.5 
//
// 设计一个支持以下两种操作的数据结构： 
//
// 
// void addNum(int num) - 从数据流中添加一个整数到数据结构中。 
// double findMedian() - 返回目前所有元素的中位数。 
// 
//
// 示例： 
//
// addNum(1)
//addNum(2)
//findMedian() -> 1.5
//addNum(3) 
//findMedian() -> 2 
//
// 进阶: 
//
// 
// 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？ 
// 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？ 
// 
// Related Topics 设计 双指针 数据流 排序 堆（优先队列） 👍 700 👎 0

package leetcode.editor.cn;

import sun.reflect.generics.tree.Tree;

import java.util.*;

public class P295FindMedianFromDataStream {
    public static void main(String[] args) {
        MedianFinder solution = new P295FindMedianFromDataStream().new MedianFinder();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class MedianFinder {
        private PriorityQueue<Integer> headQueue;
        private PriorityQueue<Integer> tailQueue;

        public MedianFinder() {
            headQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
            tailQueue = new PriorityQueue<>((o1, o2) -> o1 - o2);

        }

        public void addNum(int num) {
//            Integer headPeek = headQueue.peek();
//            if (Objects.isNull(headPeek)) {
//                headQueue.offer(num);
//                return;
//            }
//            Integer tailPeek = tailQueue.peek();
//            if (Objects.isNull(tailPeek)) {
//                tailQueue.offer(num);
//                return;
//            }
            if (headQueue.size() == 0 || num <= headQueue.peek()) {
                headQueue.offer(num);
                if (headQueue.size() - 1 > tailQueue.size()) {
                    tailQueue.offer(headQueue.poll());
                }
            } else {
                tailQueue.offer(num);
                if (headQueue.size() < tailQueue.size()) {
                    headQueue.offer(tailQueue.poll());
                }
            }
        }

        public double findMedian() {
            if (headQueue.size() == tailQueue.size()) {
                return (headQueue.peek() + tailQueue.peek()) / 2.0;
            }
            return headQueue.peek();
        }
    }


/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
