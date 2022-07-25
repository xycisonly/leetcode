package com.xyc.leetcode;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 题目写程序实现大数相加例如："98662" + "2383489"
 */
public class aa {
    public static void main(String[] args) {
        StringBuilder append = new StringBuilder().append("2append34123");


        String substring = append.substring(0, append.length()-1);
        System.out.println(substring);
    }

    private static String dealChars(String str1, String str2) {
        LinkedList<Integer> result = new LinkedList<>();
        if (Objects.isNull(str1) || str1.length() == 0) {
            str1 = "0";
        }
        if (Objects.isNull(str2) || str2.length() == 0) {
            str2 = "0";
        }

        if (str1.length() > str2.length()) {
            String str = str1;
            str1 = str2;
            str2 = str;
        }
        int str1Index = str1.length() - 1;
        int str2Index = str2.length() - 1;
        int add = 0;
        while (str1Index >= 0 && str2Index >= 0) {
            int currentResult = add + Integer.parseInt(String.valueOf(str1.charAt(str1Index))) + Integer.parseInt(String.valueOf(str2.charAt(str2Index)));

            if (currentResult > 9) {
                currentResult = currentResult % 10;
                add = 1;
            } else {
                add = 0;
            }

            result.addFirst(currentResult);
            str1Index--;
            str2Index--;
        }
        while (str2Index >= 0 || add > 0) {
            int currentResult = add;
            if (add>0){
                add = 0;
            }
            if (str2Index >= 0) {
                currentResult += Integer.parseInt(String.valueOf(str2.charAt(str2Index)));
                if (currentResult > 9) {
                    currentResult = currentResult % 10;
                    add = 1;
                }
                str2Index--;
            }
            result.addFirst(currentResult);
        }
        StringBuilder sb = new StringBuilder();
        while (!result.isEmpty()) {
            sb.append(result.removeFirst());
        }
        return sb.toString();
    }

}
