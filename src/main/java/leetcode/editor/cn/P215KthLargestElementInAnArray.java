//给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。 
//
// 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。 
//
// 
//
// 示例 1: 
//
// 
//输入: [3,2,1,5,6,4] 和 k = 2
//输出: 5
// 
//
// 示例 2: 
//
// 
//输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
//输出: 4 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= nums.length <= 10⁴ 
// -10⁴ <= nums[i] <= 10⁴ 
// 
// Related Topics 数组 分治 快速选择 排序 堆（优先队列） 👍 1667 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P215KthLargestElementInAnArray {
    public static void main(String[] args) {
        Solution solution = new P215KthLargestElementInAnArray().new Solution();
        solution.findKthLargest(new int[]{-1,2,0},1);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findKthLargest(int[] nums, int k) {
            quickSelect(nums, 0, nums.length - 1, nums.length-k);
            return nums[nums.length-k];
        }

        // 3,1,6,5,4
        private void quickSelect(int[] nums, int left, int right, int k) {
            if (left >= right) {
                return;
            }
            int begin = left;
            int end = right;
            int index = left;
            int target = nums[index];
            while (left < right) {
                while (nums[right] >= target && left < right) right--;
                while (nums[left] <= target && left < right) left++;
                if (left < right) {
                    swap(nums, left, right);
                }
            }
            nums[index] = nums[left];
            nums[left] = target;
            if (left == k) {
                return;
            } else if (left < k) {
                quickSelect(nums, left + 1, end, k);
            } else {
                quickSelect(nums, begin, left - 1, k);
            }
        }

        private void swap(int[] nums, int left, int right) {
            int leftNum = nums[left];
            nums[left] = nums[right];
            nums[right] = leftNum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}