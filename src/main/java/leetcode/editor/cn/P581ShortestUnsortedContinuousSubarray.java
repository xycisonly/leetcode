
//给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
//
// 你找到的子数组应是最短的，请输出它的长度。 
//
// 示例 1: 
//
// 
//输入: [2, 6, 4, 8, 10, 9, 15]
//输出: 5
//解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
// 
//
// 说明 : 
//
// 
// 输入的数组长度范围在 [1, 10,000]。 
// 输入的数组可能包含重复元素 ，所以升序的意思是<=。 
// 
// Related Topics 数组 
// 👍 354 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P581ShortestUnsortedContinuousSubarray {
    public static void main(String[] args) {
        Solution solution = new P581ShortestUnsortedContinuousSubarray().new Solution();
        System.out.println(solution.findUnsortedSubarray(new int[]{1,2,3}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findUnsortedSubarray(int[] nums) {
            int maxSortedIndex = 0;
            int maxSortedValue = Integer.MIN_VALUE;
            for (int index = 0;index <nums.length;index++){
                int num = nums[index];
                if (num<maxSortedValue){
                    break;
                }
                maxSortedValue = num;
                maxSortedIndex = index;
            }
            int minSortedIndex = nums.length-1;
            int minSortedValue = Integer.MAX_VALUE;
            for (int index = nums.length-1 ;index>maxSortedIndex; index--){
                int num = nums[index];
                if (num>minSortedValue){
                    break;
                }
                minSortedValue = num;
                minSortedIndex = index;
            }
            int minNotSortedValue = Integer.MAX_VALUE;
            int maxNotSortedValue = Integer.MIN_VALUE;
            for (int index = maxSortedIndex ;index <= minSortedIndex;index++){
                int num = nums[index];
                minNotSortedValue = Math.min(num, minNotSortedValue);
                maxNotSortedValue = Math.max(num, maxNotSortedValue);
            }
            int begin = 0;
            for (int index = 0;index <= maxSortedIndex;index++){
                if (minNotSortedValue<nums[index]){
                    break;
                }
                begin++;
            }

            int end = nums.length-1;
            for (int index = nums.length-1 ;index >= minSortedIndex; index--){
                if (maxNotSortedValue>nums[index]){
                    break;
                }
                end--;
            }
            int result = end -begin+1;
            return Math.max(result, 0);
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}