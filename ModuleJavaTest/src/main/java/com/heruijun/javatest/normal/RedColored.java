package com.heruijun.javatest.normal;

/**
 * Created by heruijun on 2018/3/30.
 */

public class RedColored<T extends Color> {
    public T t;

    public void color() {
        t.getColor();       // T的边界值是Color，所以可以调用getColor()，否则会编译错
    }
}

abstract class Color {
    abstract void getColor();
}