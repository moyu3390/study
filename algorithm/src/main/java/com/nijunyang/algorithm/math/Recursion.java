package com.nijunyang.algorithm.math;

/**
 * Description: 递归
 * Created by nijunyang on 2020/4/9 14:17
 */
public class Recursion {



    public static void main(String[] args){
        System.out.println("---裴波那契数列普通递归---");
        long time1 = System.currentTimeMillis();
        int result = recursion(45);
        System.out.println(result);
        long time2 = System.currentTimeMillis();
        System.out.println(time2-time1);
        System.out.println("---裴波那契数列尾递归---");
        result = tailRecursion(1,1,45);
        System.out.println(result);
        long time3 = System.currentTimeMillis();
        System.out.println(time3-time2);

//        result = cycle(30);
//        System.out.println(result);
//        result = cache(30);
//        System.out.println(result);

//        long l = System.currentTimeMillis();
//        System.out.println(factorial(30));
//        long l2 = System.currentTimeMillis();
//        System.out.println(l2-l);
//        System.out.println(taiFactorial(30, 1));
//        long l3 = System.currentTimeMillis();
//        System.out.println(l3-l2);

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
     * 裴波那契数列循环实现
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

    /**
     * 尾递归 裴波那契
     * @param pre 上上一次运算出来的结果
     * @param result  上一次运算出来结果
     * @param n
     * @return
     */
    public static int tailRecursion(int pre, int result, int n) {
        if (n <= 2) {
            return result;
        }
        //对于下一次调用来说 前一次结果 pre + result  前前一次result
        return tailRecursion(result, pre + result, n - 1);
    }

    public static int cache(int n) {
        int data[] = new int[n]; // 用数组来做缓存
        return fac(n, data);
    }

    public static int fac(int n, int[] data) {
        if (n <= 2)
            return 1; //递归的终止条件
        if (data[n-1] > 0) {  //数组中有值就直接取出来返回，不用再去计算
            return data[n-1];
        }
        int res = fac(n - 1, data) + fac(n - 2, data);
        data[n-1] = res;  //算出来值放到数组中
        return res;
    }


    /**
     * 阶乘
     * @param n
     * @return
     */
    public static int factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    /**
     * 阶乘尾递归
     * @param n
     * @return
     */
    public static int taiFactorial(int n, int result) {
        if (n <= 1) {
            return result; //最后返回的即是最终结果
        }
        return taiFactorial(n - 1, n * result);//结果往下传
    }




}
