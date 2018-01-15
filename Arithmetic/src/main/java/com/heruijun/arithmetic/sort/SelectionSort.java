package com.heruijun.arithmetic.sort;

import java.util.Arrays;

/**
 * 选择排序
 *
 * Created by heruijun on 2018/1/15.
 */

public class SelectionSort {

    private SelectionSort() {

    }

    private static int[] sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }

        return arr;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{8, 1, 9, 3, 2, 5, 6, 7};
        System.out.print(Arrays.toString(sort(arr)));
    }

}