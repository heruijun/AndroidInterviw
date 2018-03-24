package com.heruijun.javatest.normal;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by heruijun on 2018/3/5.
 */

public class Test {

    static Map<String, ? super Animal> map = new HashMap();

    public static void main(String[] args) {
        MyCat aCat = new MyCat();
        map.put("a", aCat);
        ((MyCat)map.get("a")).say();
    }

}
