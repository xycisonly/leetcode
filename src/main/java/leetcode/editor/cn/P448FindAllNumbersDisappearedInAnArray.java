//给定一个范围在 1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。 
//
// 找到所有在 [1, n] 范围之间没有出现在数组中的数字。 
//
// 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。 
//
// 示例: 
//
// 
//输入:
//[4,3,2,7,8,2,3,1]
//
//输出:
//[5,6]
// 
// Related Topics 数组 
// 👍 415 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P448FindAllNumbersDisappearedInAnArray {
    public static void main(String[] args) {
        Solution solution = new P448FindAllNumbersDisappearedInAnArray().new Solution();
        System.out.println(solution.findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public ArrayList<Integer> findDisappearedNumbers(int[] nums) {
            for (int index = 0; index < nums.length; ) {
                int num = Math.abs(nums[index]);
                if (nums[num - 1] > 0) {
                    nums[num - 1] *= -1;
                }
                index++;
            }
            ArrayList<Integer> result = new ArrayList<>();
            for (int index = 0; index < nums.length; index++) {
                if (nums[index] > 0) {
                    result.add(index + 1);
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}