package com.heruijun.arithmetic.zhimore;

import android.content.Intent;

/**
 * Created by heruijun on 2018/1/16.
 */

public class Test1 {

    /**
     * @param A
     * @param N 数组长度
     * @return
     */
    int breakchain(int A[], int N) {
        return 0;
    }

    // 生成有n个元素的随机数组,每个元素的随机范围为[rangeL, rangeR]
    public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {
        if(n < 5 || n > 100000) {
            throw new IllegalArgumentException("数组长度必须在5至100000之间");
        }
        assert rangeL <= rangeR;
        Integer[] arr = new Integer[n];

        for (int i = 0; i < n; i++)
            arr[i] = new Integer((int) (Math.random() * (rangeR - rangeL + 1) + rangeL));
        return arr;
    }

    // 打印arr数组的所有内容
    public static void printArray(Object arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(' ');
        }
        System.out.println();
        return;
    }

    public static void main(String[] args){
        Integer[] A = generateRandomArray(10, 1,1000000000);
        printArray(A);
    }

}
