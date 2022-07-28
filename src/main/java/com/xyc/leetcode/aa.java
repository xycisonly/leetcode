package com.xyc.leetcode;


import java.util.*;

/**
 * 题目写程序实现大数相加例如："98662" + "2383489"
 */
public class aa {
    public static void main(String[] args) {
        int[] a = {10, 10, -20};
        List<List<Integer>> result = find(a);
        System.out.println(result);
    }

    public static List<List<Integer>> find(int[] nums) {
        List<List<Integer>> result = new ArrayList();
        if (null == nums || nums.length < 2) {
            return result;
        }
        int leftIndex = -1;
        int rightIndex = -1;

        int unUseLeft = 0;
        int unUseRight = 0;

        while (true) {
            System.out.println(leftIndex+""+rightIndex);
            if (unUseLeft == 0) {
                leftIndex++;
                while (leftIndex < nums.length && nums[leftIndex] <= 0) {
                    leftIndex++;
                }
                if (leftIndex == nums.length) {
                    break;
                }
                unUseLeft = nums[leftIndex];
            }
            if (unUseRight == 0) {
                rightIndex++;
                while (rightIndex < nums.length && nums[rightIndex] >= 0) {
                    rightIndex++;
                }
                if (rightIndex == nums.length) {
                    break;
                }
                unUseRight = nums[rightIndex];
            }

            if (unUseLeft > -unUseRight) {
                result.add(Arrays.asList(leftIndex, rightIndex, -unUseRight));
                unUseLeft = unUseLeft + unUseRight;
                unUseRight = 0;
            } else if (unUseLeft < -unUseRight) {
                result.add(Arrays.asList(leftIndex, rightIndex, unUseLeft));
                unUseRight += unUseLeft;
                unUseLeft = 0;
            } else {
                result.add(Arrays.asList(leftIndex, rightIndex, -unUseRight));
                unUseRight = 0;
                unUseLeft = 0;
            }
        }
        return result;
    }

}
