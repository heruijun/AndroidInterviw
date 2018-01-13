package com.heruijun.javatest.normal;

import java.lang.ref.WeakReference;

/**
 * Created by heruijun on 2018/1/13.
 */

public class WeakReferenceTest {

    private void init() {
        A a = new A();
        WeakReference<A> wf = new WeakReference<A>(a);
        a = null;
        if (wf.get() != null) {
            ((A) wf.get()).say();
        }
    }

    class A {
        private void say() {
            System.out.println("hello");
        }
    }

    public static void main(String[] args) {
        WeakReferenceTest wt = new WeakReferenceTest();
        wt.init();
    }

}
