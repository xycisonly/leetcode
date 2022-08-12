package com.xyc.leetcode;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        AwaitAndSignal awaitAndSignal = new AwaitAndSignal(5);
        Condition conditionA = awaitAndSignal.newCondition();
        Condition conditionB = awaitAndSignal.newCondition();
        Condition conditionC = awaitAndSignal.newCondition();
        new Thread(() -> {
            awaitAndSignal.print("a", conditionA, conditionB);
        }).start();
        new Thread(() -> {
            awaitAndSignal.print("b", conditionB, conditionC);
        }).start();
        new Thread(() -> {
            awaitAndSignal.print("c", conditionC, conditionA);
        }).start();
        //等他们都在休息室后还需要推他一把 不然都在休息室
        TimeUnit.SECONDS.sleep(2);
        awaitAndSignal.lock();
        try {
            conditionA.signalAll();
        } finally {
            awaitAndSignal.unlock();
        }

    }
}

class AwaitAndSignal extends ReentrantLock {
    private int loopnum;

    public AwaitAndSignal(int loopnum) {
        this.loopnum = loopnum;
    }

    public void print(String str, Condition current, Condition next) {

        this.lock();
        try {
            for (int i = 0; i < loopnum; i++) {
                //先进入休息室
                current.await();
                System.out.println(str);
                next.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.unlock();
        }


    }
}
