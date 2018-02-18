package com.heruijun.javatest.normal;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by heruijun on 2018/2/18.
 */

public class HashSetTest {

    private Set<String> mySet = new HashSet<>();

    public static void main(String[] args) {
        HashSetTest st = new HashSetTest();
        st.mySet.add("1");
        st.mySet.add("2");
        st.mySet.add("3");

        for (String s : st.mySet) {
            System.out.println(st.mySet.size() + ", " + s);
        }
    }
}
