package com.nijunyang.algorithm.math;

import java.util.Arrays;

/**
 * Description:
 * Created by nijunyang on 2020/4/10 13:50
 */
public class Sort {


    public static void main(String[] args){

        int[] arr = {5,8,9,6,6,9,7,2,6,45,98,78};
//        insertSort(arr);
//        mergeSort(arr, 0, arr.length - 1);
//        bubbleSort(arr);
//        selectionSort(arr);
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 插入排序 稳定
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

    public static void shellSort(int[] arr) {
        int len = arr.length;
        //增量每次折半,最后增量为1的时候就是最后一次排序
        for (int increment = len / 2; increment >= 1; increment /= 2) {
            //类似插入排序
            for (int i = increment; i < len; i++) {  //按增量分开使用插入排序
                int data = arr[i];
                for (int j = i - increment; j >= 0 ; j -= increment) {
                    if (arr[j] > data) {
                        arr[j + increment] = arr[j]; //位置后移
                        arr[j] = data;
                    }
                    else {
                        break; // 前面都是排好序的，有一个比它小的，再前面肯定更小，直接可以跳出，不用再比较
                    }
                }
            }

        }

    }

    /**
     * 冒泡排序 稳定  依次比较相邻的两个元素大小，每轮结束就可以选出来一个最大或者最小的
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {  //外层循环控制轮次
            for (int j = 0; j < arr.length - 1 - i; j++) { //内层循环次数越来越少，因为内层跑完一次就找到一个数
                if (arr[j] > arr[j+1]) { //交换位置
                    arr[j] = arr[j] + arr[j+1];
                    arr[j+1] = arr[j] - arr[j+1];
                    arr[j] = arr[j] - arr[j+1];
                }
            }

        }
    }

    /**
     * 选择排序 不稳定 先拿出一个数，去依次遍历，找到最大或者最小的。每轮换一次位置，冒泡是每次比较都可能会换位置。
     * @param arr
     */
    public static void selectionSort(int[] arr) {
        int tempIndex;
        for (int i = 0; i < arr.length; i++) {
            tempIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[tempIndex]) {   //比较大小 记录下标位置
                    tempIndex = j;
                }
            }
            if (tempIndex != i) {  //交换位置
                arr[i] = arr[i] + arr[tempIndex];
                arr[tempIndex] = arr[i] - arr[tempIndex];
                arr[i] = arr[i] - arr[tempIndex];
            }

        }
    }



    /**
     * 归并排序 JDK源码java.util.Arrays#mergeSort()
     * @param arr
     * @param left
     * @param right
     */
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);           //拆了之后的左
            mergeSort(arr, mid + 1, right); //拆了之后的右
            merge(arr,  left, mid, right);       //合并
        }
    }

    /**
     * 归并
     */
    private static void merge(int[] arr, int left, int mid, int right) {
        int temp[] = new int[arr.length];

        int point1 = left;
        int point2 = mid + 1;

        int loc = left;
        while(point1 <= mid && point2 <= right){
            if (arr[point1] < arr[point2]) {
                temp[loc] = arr[point1];
                point1++;
            } else {
                temp[loc] = arr[point2];
                point2++;
            }
            loc++;
        }
        while(point1 <= mid){
            temp[loc ++] = arr[point1 ++];
        }
        while(point2 <= right){
            temp[loc ++] = arr[point2 ++];
        }
        for(int i = left ; i <= right ; i++){
            arr[i] = temp[i];
        }
    }

}
