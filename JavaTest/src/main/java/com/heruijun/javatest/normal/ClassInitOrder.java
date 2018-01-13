package com.heruijun.javatest.normal;

/**
 * Created by heruijun on 2018/1/12.
 */

public class ClassInitOrder {

    public ClassInitOrder() {
        System.out.println("3");
    }

    static {
        System.out.println("1");
    }

    {
        System.out.println("2");
    }

    private void say() {
        System.out.println("4");
    }

    public static void main(String[] args) {
        ClassInitOrder initOrder = new ClassInitOrder();
        initOrder.say();
    }

}
