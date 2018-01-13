package com.heruijun.javatest.paradigm;

/**
 * Created by heruijun on 2018/1/12.
 */

public class Box<T> {

    private T t;

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

}
