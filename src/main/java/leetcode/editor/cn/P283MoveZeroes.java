//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 示例: 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针 
// 👍 668 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P283MoveZeroes {
    public static void main(String[] args) {
        Solution solution = new P283MoveZeroes().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void moveZeroes(int[] nums) {
            if (null == nums){
                return;
            }
            for (int index = 0, notZeroIndex = 0; index < nums.length; index++) {
                if ( nums[notZeroIndex] != 0) {
                    notZeroIndex++;
                    continue;
                }
                if (nums[index] != 0){
                    nums[notZeroIndex] = nums[index];
                    nums[index] = 0;
                    notZeroIndex++;
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}