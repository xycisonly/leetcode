//给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, 
//ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。 
//
// 说明：你不能倾斜容器，且 n 的值至少为 2。 
//
// 
//
// 
//
// 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。 
//
// 
//
// 示例： 
//
// 输入：[1,8,6,2,5,4,8,3,7]
//输出：49 
// Related Topics 数组 双指针

package leetcode.editor.cn;

import java.util.*;

public class P11ContainerWithMostWater {
    public static void main(String[] args) {
        Solution solution = new P11ContainerWithMostWater().new Solution();
        System.out.println(solution.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxArea(int[] height) {
            int result = 0;
            int left = 0;
            int right = height.length - 1;
            while (left < right) {
                if (height[left] <= height[right]) {
                    result = Math.max(height[left] * (right - left), result);
                    left++;
                } else {
                    result = Math.max(height[right] * (right - left), result);
                    right--;
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}