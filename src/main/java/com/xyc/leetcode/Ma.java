package com.xyc.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author xiaoyuchen
 * @date 2022/5/12
 */
public class Ma {
    private static volatile int num = 1;

    public static void main(String[] args) {
        doit(4,500);
    }

    public static void doit(int totalThread, int maxValue) {
        List<CountDownLatch> countDownLatchList = new ArrayList<>();
        for (int threadNumber = 1; threadNumber <= totalThread; threadNumber++) {
            countDownLatchList.add(new CountDownLatch(1));
        }
        for (int threadNumber = 1; threadNumber <= totalThread; threadNumber++) {
            int finalThreadNumber = threadNumber-1;
            new Thread(()-> {
                try {
                    threadDo(countDownLatchList,maxValue, finalThreadNumber);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }).start();
        }
        countDownLatchList.get(0).countDown();
    }

    public static void threadDo(List<CountDownLatch> countDownLatchList, int maxValue, int threadNumber) throws InterruptedException {
        while (num <= maxValue) {
            CountDownLatch countDownLatch = countDownLatchList.get(threadNumber);
            countDownLatch.await();
            if (num > maxValue) {
                break;
            }
            System.out.println(num++);
            countDownLatchList.set(threadNumber, new CountDownLatch(1));
            countDownLatchList.get(threadNumber == countDownLatchList.size() - 1 ? 0 : threadNumber + 1).countDown();
        }
    }
}
