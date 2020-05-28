//给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。 
//
// 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。 
//
// 你可以假设 nums1 和 nums2 不会同时为空。 
//
// 
//
// 示例 1:

//
// nums1 = [1, 3]
//nums2 = [2]
//
//则中位数是 2.0
// 
//
// 示例 2: 
//
// nums1 = [1, 2]
//nums2 = [3, 4]
//
//则中位数是 (2 + 3)/2 = 2.5
// 
// Related Topics 数组 二分查找 分治算法

package leetcode.editor.cn;

import java.math.BigDecimal;
import java.util.*;


public class P4MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new P4MedianOfTwoSortedArrays().new Solution();
        System.out.println(solution.findMedianSortedArrays(new int[]{1},new int[]{2,3,4,5,6,7}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
        class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            //划分数组法
            int len1 = nums1.length,len2 = nums2.length;
            if (len1>len2){
                return findMedianSortedArrays(nums2,nums1);
            }
            //划分线左边有几个数字
            int divide1 = len1/2 , divide2 = (len1+len2)/2 -divide1;
            while (true){
                if (divide1!=len1&&divide2!=0&&nums1[divide1]<nums2[divide2-1]){
                    divide1++;divide2--;
                } else if (divide2!=len2&&divide1!=0&&nums2[divide2]<nums1[divide1-1]){
                    divide2++;divide1--;
                } else {
                    int a,b;
                    if (divide1==0){
                        if (divide2==len2){
                            a = nums2[len2-1];
                            b = nums1[0];
                        }else {
                            int divide = (divide2+len2)/2;
                            a = nums2[divide-1];
                            b = nums2[divide];
                        }
                    }else if (divide1==len1){
                        if (divide2<=1){
                            a = nums1[len1-1] ;
                            b = nums2[0];
                        }else {
                            a = nums2[divide2/2-1];
                            b = nums2[divide2/2];
                        }
                    }
                    if ((len1+len2)%2==0){
                        //取平均值
                        return (Math.max(nums1[divide1-1],nums2[divide2-1])+Math.min(nums1[divide1],nums2[divide2]))*0.5;
                    }else{
                        return Math.min(nums1[divide1],nums2[divide2]);
                    }
                }

            }
        }
    }
//    class Solution {
//        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//            //删除法，删除中位数左边
//            //初始化
//            int nums1Left = 0, num1Right = nums1.length - 1, num2Right = nums2.length - 1, nums2Left = 0;
//
//            int len =  nums1.length +  nums2.length;
//            //
//            while (true){
//                int num1Len = num1Right - nums1Left + 1, num2Len = num2Right - nums2Left + 1;
//                int median = (len + 1)/2 ;
//                if (num1Len==0){
//                    if (len%2==0){
//                        return (nums2[nums2Left+median]+nums2[nums2Left+median-1])*0.5;
//                    }else {
//                        return nums2[nums2Left+median-1];
//                    }
//                }else if (num2Len==0){
//                    if (len%2==0){
//                        return (nums1[nums1Left+median]+nums1[nums1Left+median-1])*0.5;
//                    }else {
//                        return nums1[nums1Left+median-1];
//                    }
//                }
//                int delete = median/2;
//                if (delete==0){
//                    if (len%2==0){
//                        //两个数字取均值
//                        int num1 = nums1[nums1Left], num3 = num1Len==1?Integer.MAX_VALUE:nums1[nums1Left+1],
//                                num2 = nums2[nums2Left], num4 = num2Len==1?Integer.MAX_VALUE:nums2[nums2Left+1];
//                        if (num1>=num2){
//                            return (num2+Math.min(Math.min(num1,num3),num4))*0.5;
//                        }else {
//                            return (num1+Math.min(Math.min(num2,num3),num4))*0.5;
//                        }
//                    }else {
//                        //一个数
//                        return Math.min(nums1[nums1Left],nums2[nums2Left]);
//                    }
//                }
//                int num1Index = delete>num1Len?num1Len-1:nums1Left+delete-1;
//                int num2Index = delete>num2Len?num2Len-1:nums2Left+delete-1;
//                //移除最小的
//                if (nums1[num1Index]<nums2[num2Index]){
//                    int deleteNum = Math.min(delete, num1Len);
//                    nums1Left = nums1Left + deleteNum;
//                    len = len - deleteNum*2;
//                }else {
//                    int deleteNum = Math.min(delete, num2Len);
//                    nums2Left = nums2Left + deleteNum;
//                    len = len - deleteNum*2;
//                }
//            }
//        }
//    }
//leetcode submit region end(Prohibit modification and deletion)

}