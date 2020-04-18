package com.nijunyang.algorithm.math;

/**
 * Description: 动态规划
 * Created by nijunyang on 2020/4/18 10:46
 */
public class Dp {

    public static void main(String[] args) {
        System.out.println(dynamicProgramming());
    }


    public static int dynamicProgramming() {
        int[] money ={10,6,12};
        int[] weight = {2,1,4};
        int w = 5;  //包容量
        int count = 3;
        int result[][] = new int[count+1][w+1]; //容量是从1开始，都扩大一位防止越界,放置物品也从一开始，因为第一次也要取前一次的数据，保证前一次有默认数据

        for (int i = 1; i <= count; i++) {   //依次装物品
            for (int currentW = 1;  currentW <= w; currentW++) { //背包容量依次增加
                if (weight[i - 1] <= currentW) { //当前物品的容量小于当前背包重量表示可以装进去
                    int freeMoney = result[i - 1][currentW - weight[i - 1]];  //装完当前物品，余下空间的价值
                    int notHaveCurrent = result[i - 1][currentW]; //不要当前物品的价值，也就是前面一次的价值
                    result[i][currentW] = Math.max(money[i - 1] + freeMoney, notHaveCurrent);
                }
                else { //不能装当前 取前一次
                    result[i][currentW] = result[i - 1][currentW];
                }
            }
        }
        return result[count][w];
    }

}
