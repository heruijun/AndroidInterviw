package com.heruijun.javatest.normal;

/**
 * 模拟栈溢出异常：java.lang.StackOverflowError
 * 
 * Created by heruijun on 2018/1/13.
 */

public class StackOverflowMock {

    private void test(){
        System.out.println("hello");
        test();
    }

    public static void main(String[] args) {
        StackOverflowMock sf = new StackOverflowMock();
        sf.test();
    }

}
