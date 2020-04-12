package com.nijunyang.algorithm.math;

import java.util.Arrays;

/**
 * Description:
 * Created by nijunyang on 2020/4/10 13:50
 */
public class Sort {


    public static void main(String[] args){

//        int[] arr = {5,8,9,6,7,3,1,4};
//        printArr(arr);
//        insertSort(arr);
//        printArr(arr);
        int[] arr = {5,8,9,6};
        mergeSort(arr,0, arr.length - 1);
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

    public static void printArr(int[] arr) {
        for (int a : arr) {
            System.out.print(a);
            System.out.print(",");
        }
        System.out.println();
    }
}
