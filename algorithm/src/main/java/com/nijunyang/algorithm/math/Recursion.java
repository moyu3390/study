package com.nijunyang.algorithm.math;

/**
 * Description: 递归
 * Created by nijunyang on 2020/4/9 14:17
 */
public class Recursion {

    public static void main(String[] args){
        int result = recursion(50);
        System.out.println(result);
        result = cycle(50);
    }

    /**
     * 裴波那契数列 递归
     * @param n
     * @return
     */
    public static int recursion(int n) {
        if (n <= 2) {
            return n == 0 ? 0 : 1;
        }
        return recursion(n - 1) + recursion(n - 2);
    }

    /**
     * 循环实现
     * @param n
     * @return
     */
    public static int cycle(int n) {
        if (n <= 2) {
            return n <= 0 ? 0 : 1;
        }
        int f1 = 1; //n-1
        int f2 = 1; //n-2
        int fn = 0; // n
        for (int i =3; i<=n;i++) {
            fn = f1 + f2;
            f2 = f1;
            f1 = fn ;
        }
        return fn ;
    }

    public static int tailRecursion(int pre,int res,int n) {
        if (n <= 2)
            return res; // 递归的终止条件
        return tailRecursion(res, pre + res, n - 1);
        //参数：
        /**
         * n 是肯定有的
         * res 上一次运算出来结果
         * pre 上上一次运算出来的结果
         */
    }




}
