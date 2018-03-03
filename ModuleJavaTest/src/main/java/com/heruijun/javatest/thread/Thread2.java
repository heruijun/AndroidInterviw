package com.heruijun.javatest.thread;

/**
 * Created by heruijun on 2018/1/12.
 */

public class Thread2 implements Runnable {

    int i = 0;

    @Override
    public void run() {
        for (; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "  " + i);
        }
    }

    public static void main(String[] args) {
        new Thread(new Thread2()).start();
        new Thread(new Thread2()).start();
    }
}
