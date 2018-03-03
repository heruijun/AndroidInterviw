package com.heruijun.javatest.normal;

/**
 * Created by heruijun on 2018/1/12.
 */

public class OverrideTest {

    private void method(String s) {
        System.out.print("a");
    }

    private void method(Object o) {
        System.out.print("b");
    }

    public static void main(String[] args) {
        OverrideTest ot = new OverrideTest();
        ot.method(null);
    }

}
