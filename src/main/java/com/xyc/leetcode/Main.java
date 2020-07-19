package com.xyc.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static final AtomicInteger[] numberCollect = new AtomicInteger[10];
    static {
        for (int a=0;a<10;a++){
            numberCollect[a] = new AtomicInteger();
        }
    }
    private static volatile long secondTime = 0;
    public static void main(String[] args) throws InterruptedException {
        //Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);
        int threadNum = 4;
        CountDownLatch count = new CountDownLatch(threadNum);
        for (int a = 0;a<threadNum;a++){
            new Thread(() -> {
                HashMap<Integer, Boolean> objectObjectHashMap = new HashMap<>();
                for (int index = 0;index<1000;index++){
                    objectObjectHashMap.put(index,
                            limiting());
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(objectObjectHashMap);
                count.countDown();
            }).start();
        }
        count.await();
        System.out.println(Arrays.toString(numberCollect));
    }

    public static boolean limiting() {
        long secondTimeNow = (System.currentTimeMillis() / 1000);
        int numberCollectIndex = Long.valueOf(secondTimeNow%10).intValue();
        while (true){
            if (secondTimeNow==secondTime){
                return numberCollect[numberCollectIndex].getAndIncrement()<100;
            }else {
                synchronized (Main.class){
                    if (secondTimeNow!=secondTime){
                        secondTime = secondTimeNow;
                        numberCollect[numberCollectIndex].set(0);
                    }
                }
            }
        }

    }


}