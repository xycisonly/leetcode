//给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[
//b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）： 
//
// 
// 0 <= a, b, c, d < n 
// a、b、c 和 d 互不相同 
// nums[a] + nums[b] + nums[c] + nums[d] == target 
// 
//
// 你可以按 任意顺序 返回答案 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,0,-1,0,-2,2], target = 0
//输出：[[-2,-1,1,2,0],[-2,0,0,2],[-1,0,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,2,2,2,2], target = 8
//输出：[[2,2,2,2]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// -10⁹ <= nums[i] <= 10⁹ 
// -10⁹ <= target <= 10⁹ 
// 
// Related Topics 数组 双指针 排序 👍 1236 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P18FourSum {
    public static void main(String[] args) {
        Solution solution = new P18FourSum().new Solution();
        solution.fourSum(new int[]{1,0,-1,0,-2,2}, 0);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> result = new ArrayList<>();
            if (null == nums || nums.length < 4) return result;
            Arrays.sort(nums);

            for (int index1 = 0; index1 < nums.length - 3; index1++) {
                if (nums[index1] > target && nums[index1 + 1] > 0) {
                    break;
                }
                for (int index2 = index1 + 1; index2 < nums.length - 2; index2++) {
                    int sumIndex = nums[index1] + nums[index2];
                    if (sumIndex > target && nums[index2 + 1] > 0) {
                        break;
                    }

                    int left = index2 + 1, right = nums.length - 1;
                    while (left < right) {
                        int sum = sumIndex + nums[left] + nums[right];
                        if (sum == target) {
                            result.add(Arrays.asList(nums[index1], nums[index2], nums[left], nums[right]));
                            left++;
                            right--;
                            while (left < right && nums[left] == nums[left - 1]) left++;
                            while (left < right && nums[right] == nums[right + 1]) right--;
                        } else if (sum > target) {
                            right--;
                        } else {
                            left++;
                        }
                    }
                    while (index2 < nums.length - 2 && nums[index2] == nums[index2 + 1]) index2++;
                }
                while (index1 < nums.length - 3 && nums[index1] == nums[index1 + 1]) index1++;

            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}