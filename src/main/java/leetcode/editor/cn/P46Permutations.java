//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 6 
// -10 <= nums[i] <= 10 
// nums 中的所有整数 互不相同 
// 
// Related Topics 数组 回溯 👍 2092 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P46Permutations {
    public static void main(String[] args) {
        Solution solution = new P46Permutations().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
     class Solution {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            doPermute(nums,new LinkedHashSet<>(),result);
            return result;
        }

        private void doPermute(int[] nums,LinkedHashSet<Integer> used,List<List<Integer>> result){
            if (nums.length == used.size()){
                result.add(new ArrayList<>(used));
                return;
            }
            for (int index= 0;index<nums.length;index++){
                //已经存在了
                if (used.contains(nums[index])){
                    continue;
                }
                used.add(nums[index]);
                doPermute(nums,used,result);
                used.remove(nums[index]);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}