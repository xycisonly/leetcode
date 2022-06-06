//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
// 
//
// 示例 2： 
//
// 
//输入：height = [4,2,0,3,2,5]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 1 <= n <= 2 * 104 
// 0 <= height[i] <= 105 
// 
// Related Topics 栈 数组 双指针 动态规划 单调栈 
// 👍 3493 👎 0

package leetcode.editor.cn;

import java.util.*;

public class P42TrappingRainWater {
    public static void main(String[] args) {
        Solution solution = new P42TrappingRainWater().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int trap(int[] height) {
            int result = 0;
            if (Objects.isNull(height) || height.length < 3) {
                return result;
            }
            int left = 0;
            int right = height.length - 1;
            int leftMax = height[left];
            int rightMax = height[right];
            while (left < right - 1) {
                if (leftMax <= rightMax) {
                    left++;
                    if (height[left] >= leftMax) {
                        leftMax = height[left];
                    } else {
                        result += (Math.min(leftMax, rightMax) - height[left]);
                    }
                } else {
                    right--;
                    if (height[right] >= rightMax) {
                        rightMax = height[right];
                    } else {
                        result += (Math.min(leftMax, rightMax) - height[right]);
                    }
                }
            }
            return result;
        }

        // 使用动态规划，记录每一个递减的点，使用新增的点比对递减的点计算蓄水量
        public int trap1(int[] height) {
            int totalResult = 0;
            if (Objects.isNull(height) || height.length < 3) {
                return totalResult;
            }
            //记录递减的点index
            LinkedList<Integer> highIndexList = new LinkedList<>();
            highIndexList.add(0);
            for (int index = 1; index < height.length; index++) {
                //当找到更高点时，每次循环溢出一个最低的点，获取上一个第二低点，和当前点计算出面积。
                while (!highIndexList.isEmpty()
                        && height[highIndexList.getLast()] < height[index]) {
                    Integer low = highIndexList.removeLast();
                    if (highIndexList.isEmpty()) {
                        break;
                    }
                    Integer lastHigh = highIndexList.getLast();
                    Integer waterHigh = Math.min(height[lastHigh], height[index]) - height[low];
                    Integer waterWeight = index - lastHigh - 1;
                    totalResult += waterHigh * waterWeight;
                }
                //当前点入队列，无论是否已经放入
                highIndexList.addLast(index);
            }
            return totalResult;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}