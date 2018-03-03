package com.heruijun.arithmetic.palindrome;

/**
 * Created by heruijun on 2018/3/2.
 */

public class StringPalindrome {

    public static boolean istPalindrom(char[] word) {
        if (word.length < 2 || word.length > 500000) {
            throw new IllegalArgumentException("words at least 2 and at most 500,000 lower case English letters");
        }
        int i1 = 0;
        int i2 = word.length - 1;
        while (i2 > i1) {
            if (word[i1] != word[i2]) {
                return false;
            }
            ++i1;
            --i2;
        }
        return true;
    }

    public static void main(String[] args) {
        char[] word = new char[]{'a', 'b', 'b', 'a'};
        System.out.println(istPalindrom(word));
    }
}
