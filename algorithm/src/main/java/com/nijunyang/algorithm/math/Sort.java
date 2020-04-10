package com.nijunyang.algorithm.math;

/**
 * Description:
 * Created by nijunyang on 2020/4/10 13:50
 */
public class Sort {


    public static void main(String[] args){

        int[] arr = {5,8,9,6,7,3,1,4};
        printArr(arr);
        insertSort(arr);
        printArr(arr);
    }

    /**
     * 插入排序
     * @param arr
     */
    public static void insertSort(int[] arr) {
        int length = arr.length;
        for (int i = 1; i < length; i++) {
            int data = arr[i];
            for (int j = i - 1; j >= 0; j--) { //反向遍历比较
                if (arr[j] > data) {
                    arr[j + 1] = arr[j]; //位置后移
                    arr[j] = data;
                }
                else {
                    break; // 前面都是排好序的，有一个比它小的，再前面肯定更小，直接可以跳出，不用再比较
                }
            }
        }
    }

    /**
     * 归并排序
     */
    public static void mergeSort() {

    }

    public static void printArr(int[] arr) {
        for (int a : arr) {
            System.out.print(a);
            System.out.print(",");
        }
        System.out.println();
    }
}
