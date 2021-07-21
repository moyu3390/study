package com.nijunyang.leetcode.offer;

/**
 * @author nijunyang
 * @since 2021/6/23
 */
public class Solution15 {

    public int hammingWeight(int n) {
//        String s = Integer.toBinaryString(n);
//        char[] chars = s.toCharArray();
//        int count = 0;
//        for (char ch: chars) {
//            if (ch == '1') {
//                count++;
//            }
//        }
//        return count;
        /**
         位运算
         与运算:设二进制数字n，则有：
         若 n & 1 = 0n ，则 n 二进制 最右一位 为 0
         若 n & 1 = 1n&1=1 ，则 n 二进制 最右一位 为 1
         判断 n 最右一位是否为 1
         将 n 右移一位（无符号右移。
         */
        int count = 0;
        while (n > 0) {
            count += n & 1;
            n >>>= 1;
        }
        return count;
    }

    public static void main(String[] args) {
        Solution15 solution15 = new Solution15();
//        System.out.println(solution15.hammingWeight(11111111111111111111111111111101));
//        System.out.println(Integer.toBinaryString(5));
//        System.out.println(Integer.toBinaryString(4));
//        System.out.println(Integer.toBinaryString(3));
        System.out.println(Integer.bitCount(5));;
    }
}
