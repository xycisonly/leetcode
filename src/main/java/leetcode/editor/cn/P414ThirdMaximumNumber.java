//给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。 
//
// 
//
// 示例 1： 
//
// 
//输入：[3, 2, 1]
//输出：1
//解释：第三大的数是 1 。 
//
// 示例 2： 
//
// 
//输入：[1, 2]
//输出：2
//解释：第三大的数不存在, 所以返回最大的数 2 。
// 
//
// 示例 3： 
//
// 
//输入：[2, 2, 3, 1]
//输出：1
//解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
//此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁴ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 
//
// 
//
// 进阶：你能设计一个时间复杂度 O(n) 的解决方案吗？ 
// Related Topics 数组 排序 👍 361 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P414ThirdMaximumNumber {
    public static void main(String[] args) {
        Solution solution = new P414ThirdMaximumNumber().new Solution();
        System.out.println(-Integer.compare(Integer.MIN_VALUE,1));
        solution.thirdMax(new int[]{-2147483648,1,1,10});
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public int thirdMax(int[] nums) {
            Integer a = null, b = null, c = null;
            for (int num : nums) {
                if (a == null || num > a) {
                    c = b;
                    b = a;
                    a = num;
                } else if (a > num && (b == null || num > b)) {
                    c = b;
                    b = num;
                } else if (b != null && b > num && (c == null || num > c)) {
                    c = num;
                }
            }
            return c == null ? a : c;
        }
    }

//    class Solution {
//        public int thirdMax(int[] nums) {
//            TreeSet<Integer> treeSet = new TreeSet<>();
//            for (int element : nums) {
//                treeSet.add(element);
//                if (treeSet.size()>3){
//                    treeSet.pollFirst();
//                }
//            }
//            if (treeSet.size() < 3) {
//                return treeSet.last();
//            }else {
//                return treeSet.first();
//            }
//        }
//    }
//leetcode submit region end(Prohibit modification and deletion)

}