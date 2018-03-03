package com.heruijun.javatest.thread;

/**
 * Created by heruijun on 2018/1/12.
 */

public class Thread1 extends Thread {

    int i = 0;

    @Override
    public void run() {
        for (; i < 100; i++) {
            System.out.println(getName() + "  " + i);
        }
    }

    public static void main(String[] args) {
        new Thread1().start();
        new Thread1().start();
    }
}
