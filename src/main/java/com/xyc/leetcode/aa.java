package com.xyc.leetcode;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class aa {
    public static void main(String[] args) throws UnsupportedEncodingException {
        int b = '人';
        System.out.println(b);
        String a = "aa";
        System.out.println("UTF-8编码长度:" + a.getBytes("UTF-8").length);
        System.out.println("GBK编码长度:" + a.getBytes("GBK").length);
        System.out.println("GB2312编码长度:" + a.getBytes("GB2312").length);
        System.out.println("UNICODE编码长度:" + a.getBytes("UNICODE").length);
        System.out.println("==========================================");
    }

}
