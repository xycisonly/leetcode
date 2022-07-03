//给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
//。 
//
// 返回 滑动窗口中的最大值 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
//输出：[3,3,5,5,6,7]
//解释：
//滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], k = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// -10⁴ <= nums[i] <= 10⁴ 
// 1 <= k <= nums.length 
// 
// Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列） 👍 1687 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P239SlidingWindowMaximum {
    public static void main(String[] args) {
        Solution solution = new P239SlidingWindowMaximum().new Solution();
        System.out.println(solution.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7},3));
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 分段思想，牛逼
         * @param nums
         * @param k
         * @return
         */
        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            int[] prefixMax = new int[n];
            int[] suffixMax = new int[n];
            for (int i = 0; i < n; ++i) {
                if (i % k == 0) {
                    prefixMax[i] = nums[i];
                }
                else {
                    prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
                }
            }
            for (int i = n - 1; i >= 0; --i) {
                if (i == n - 1 || (i + 1) % k == 0) {
                    suffixMax[i] = nums[i];
                } else {
                    suffixMax[i] = Math.max(suffixMax[i + 1], nums[i]);
                }
            }

            int[] ans = new int[n - k + 1];
            for (int i = 0; i <= n - k; ++i) {
                ans[i] = Math.max(suffixMax[i], prefixMax[i + k - 1]);
            }
            return ans;
        }

        /**
         * 失效链表
         *
         * @param nums
         * @param k
         * @return
         */
        public int[] maxSlidingWindow2(int[] nums, int k) {
            LinkedList<Integer> list = new LinkedList<>();
            int[] result = new int[nums.length];
            for (int index = 0; index < nums.length; index++) {
                while (!list.isEmpty() && nums[list.peekFirst()] < nums[index]) {
                    list.pollFirst();
                }
                list.addFirst(index);
                while (list.peekLast() + k <= index) {
                    list.removeLast();
                }
                result[index] = nums[list.peekLast()];
            }
            return Arrays.copyOfRange(result, k - 1, nums.length);
        }

        /**
         * 优先级队列
         *
         * @param nums
         * @param k
         * @return
         */
        public int[] maxSlidingWindow1(int[] nums, int k) {
            PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
            for (int index = 0; index < k - 1; index++) {
                queue.add(new int[]{nums[index], index});
            }
            for (int index = 0; index < nums.length - k + 1; index++) {
                int i = index + k - 1;
                queue.offer(new int[]{nums[i], i});
                while (queue.peek()[1] < index) {
                    queue.poll();
                }
                nums[index] = queue.peek()[0];
            }

            return Arrays.copyOfRange(nums, 0, nums.length - k + 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}