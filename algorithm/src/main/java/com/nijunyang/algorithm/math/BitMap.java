package com.nijunyang.algorithm.math;

/**
 * Description:
 * Created by nijunyang on 2020/5/5 22:33
 */
public class BitMap {
    /**
     * 范围中最大的那个数
     */
    private int max;

    private int[] flag;

    public BitMap(int max) {
        this.max = max;
        flag = new int[max >> 5 + 1]; //除以32也可以表示为>>5
    }

    /**
     * 添加数据标记
     * @param val
     */
    public void put(int val) {		//往bitmap里面添加数字

        int index = val / 32;		// 计算数组索引位置
        int location = val % 32;	// 计算在32位int中的位置
        flag[index] |= 1 << location;   //标记位改成1
    }

    /**
     * 判断是否存在
     * @param val
     * @return
     */
    public boolean exist(int val) {
        int index = val / 32;
        int location = val % 32;

        int result = flag[index] & (1 << location);
        return result != 0;
    }

    /**
     * 移除标记
     * @param val
     * @return
     */
    public void remove(int val) {
        int index = val / 32;
        int location = val % 32;

        System.out.println(Integer.toBinaryString(flag[index]));
        flag[index] = flag[index] &~ (1 << location); //~取反1变0 0变1
        System.out.println(Integer.toBinaryString(flag[index]));
    }

    public static void main(String[] args) {
        BitMap bitMap = new BitMap(200_000_000);
        bitMap.put(128);
        bitMap.put(129);
        System.out.println(bitMap.exist(127));
        System.out.println(bitMap.exist(128));
        bitMap.remove(128);
        System.out.println(bitMap.exist(128));

    }
}
