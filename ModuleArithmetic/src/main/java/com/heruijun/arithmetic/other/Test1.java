package com.heruijun.arithmetic.other;

/**
 * Created by heruijun on 2018/1/18.
 */

public class Test1 {

    int findCount = 0;
    int[] array = new int[10];
    boolean[] used = new boolean[10];

    public boolean judge() {
        int sum1, sum2, sum3;
        sum1 = sum2 = sum3 = 0;
        for (int i = 1; i <= 4; i++) {
            sum1 += array[i];
        }
        for (int i = 4; i <= 7; i++) {
            sum2 += array[i];
        }
        for (int i = 7; i <= 9; i++) {
            sum3 += array[i];
        }
        sum3 += array[1];
        if (sum1 == sum2 && sum2 == sum3) {
            return true;
        }
        return false;
    }

    public void backtrack(int k, int n) {
        if (k == n) {
            if (judge()) {
                findCount++;
                System.out.print(String.format("find one! count=%d\n", findCount));
                System.out.printf("第一条边为：%d-%d-%d-%d\n", array[1], array[2], array[3], array[4]);
                System.out.printf("第二条边为：%d-%d-%d-%d\n", array[4], array[5], array[6], array[7]);
                System.out.printf("第三条边为：%d-%d-%d-%d\n", array[1], array[7], array[8], array[9]);
            }
            return;
        }
        for (int i = 1; i < n; i++) {
            if (!used[i]) {
                used[i] = true;
                array[k] = i;
                backtrack(k + 1, n);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Test1 t = new Test1();
        t.backtrack(1, 10);
    }

}
