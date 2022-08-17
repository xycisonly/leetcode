package com.xyc.leetcode;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Aa {
    static ReentrantLock lock = new ReentrantLock();
    static int index= 0;
    public static void main(String[] args) {
        aa();
    }

    public static void aa(){
        lock.lock();
        try {
            index++;
            System.out.println(index);
            if (index<10){
                aa();
            }

        }finally {
            lock.unlock();
        }
    }

}
