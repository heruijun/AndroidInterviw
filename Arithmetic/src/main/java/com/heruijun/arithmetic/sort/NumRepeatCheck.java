package com.heruijun.arithmetic.sort;

/**
 * 找出数组中相邻元素相等的数组下标
 * <p>
 * Created by heruijun on 2018/1/23.
 */

public class NumRepeatCheck {

    private int[] arr = new int[]{3, 1, 5, 2, 1, 8, 7, 7, 3, 9};

    private int checkRepeat() {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int index = i;
            if (arr[index] == arr[index + 1]) {
                System.out.println("当前相等的元素" + arr[index] + ", " + arr[index + 1]);
                return index;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        NumRepeatCheck nc = new NumRepeatCheck();
        System.out.print(nc.checkRepeat());
    }
}