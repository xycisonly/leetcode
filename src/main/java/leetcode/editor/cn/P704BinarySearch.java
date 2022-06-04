//给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否
//则返回 -1。 
//
// 
//示例 1: 
//
// 输入: nums = [-1,0,3,5,9,12], target = 9
//输出: 4
//解释: 9 出现在 nums 中并且下标为 4
// 
//
// 示例 2: 
//
// 输入: nums = [-1,0,3,5,9,12], target = 2
//输出: -1
//解释: 2 不存在 nums 中因此返回 -1
// 
//
// 
//
// 提示： 
//
// 
// 你可以假设 nums 中的所有元素是不重复的。 
// n 将在 [1, 10000]之间。 
// nums 的每个元素都将在 [-9999, 9999]之间。 
// 
// Related Topics 数组 二分查找 
// 👍 847 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P704BinarySearch {
    public static void main(String[] args) {
        Solution solution = new P704BinarySearch().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int search(int[] nums, int target) {
            if (null == nums) {
                return -1;
            }
            return find(nums, target, 0, nums.length - 1);
        }

        public int find(int[] nums, int target, int begin, int end) {
            if (begin > end) {
                return -1;
            }
            int index = (begin + end) / 2;
            if (nums[index] == target) {
                return index;
            }else if (nums[index]>target){
                return find(nums, target, begin, index-1);
            }else {
                return find(nums, target, index + 1, end);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}