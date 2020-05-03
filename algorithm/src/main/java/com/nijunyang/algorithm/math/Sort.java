package com.nijunyang.algorithm.math;

import java.util.Arrays;

/**
 * Description:
 * Created by nijunyang on 2020/4/10 13:50
 */
public class Sort {


    public static void main(String[] args){

//        int[] arr = {5,8,9,6,6,9,7,2,6,45,98,78};
        int[] arr = {9,5,6,8,0,3,7,1};
//        insertSort(arr);
//        mergeSort(arr, 0, arr.length - 1);
//        bubbleSort(arr);
//        selectionSort(arr);
//        shellSort(arr);
        quicklySort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
////        heapSort(arr);
//        int[] arr = {8, 4, 20, 7, 3, 1, 25, 14};
        heapSort(arr);
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
        int[] arrTemp = new int[arr.length];
        int leftPoint = left;         //左边第一个下标
        int rightPoint = mid + 1;     //右边第一个下标
        int currentPoint = left;      //当前下标
        //左右两边的数据对于自己来说都已经是有序的，所以就拿右边的依次和左边的进行大小比较，将对应的大小关系放入临时数组对应下标
        //左边指针走到mid位置或者右边指针走到right位置，既结束循环，两边剩下的有序数据，直接追加在临时数组后面，先左后右
        while (leftPoint <= mid && rightPoint <= right) {
            if (arr[leftPoint] < arr[rightPoint]) {
                arrTemp[currentPoint] = arr[leftPoint];
                leftPoint++;
            } else {
                arrTemp[currentPoint] = arr[rightPoint];
                rightPoint++;
            }
            currentPoint++;
        }
        //先处理左边剩下的直接放入对应位置
        while (leftPoint <= mid) {
            arrTemp[currentPoint++] = arr[leftPoint++];
        }
        //再处理右边剩下的
        while (rightPoint <= right) {
            arrTemp[currentPoint++] = arr[rightPoint++];
        }
        //将临时数组里面的数据放入原始数组中，注意只放merge的那一段的数据
        for (int i = left; i <= right; i++) {
            arr[i] = arrTemp[i];
        }
    }

    /**
     * 快速排序
     * @param arr
     */
    private static void quicklySort(int[] arr, int left, int right) {
        int base = arr[left];  //我们选第一个作为基准数,也就是每次的进来left下标
        int leftPoint = left;       //左边找的位置
        int rightPoint = right;     //右边找的位置
        while (leftPoint < rightPoint) {  //左边指针小于右边 说明还没碰到一起

            //从后往前找 如果后面的数大于等于基准数不交换，指针前移，需要的交换的时候跳出循环
            while (leftPoint < rightPoint && arr[rightPoint] >= base) {
                rightPoint--;
            }
            //上述循环跳出，并且左边指针小于右边指针 说明需要交换数据，左边因为换一个小的数据过去所以指针后移
            if (leftPoint < rightPoint) {
                int temp = arr[rightPoint];
                arr[rightPoint] = arr[leftPoint];
                arr[leftPoint] = temp;
                leftPoint++;
            }

            //切换 从前往后找 如果左边的数小于等于基准数不交换，指针后移，需要的交换的时候跳出循环
            while (leftPoint < rightPoint && arr[leftPoint] <= base) {
                leftPoint++;
            }
            //上述循环跳出，并且左边指针小于右边指针 说明需要交换数据，右边因为换了一个大的数据过去所以指针前移
            if (leftPoint < rightPoint) {
                int temp = arr[rightPoint];
                arr[rightPoint] = arr[leftPoint];
                arr[leftPoint] = temp;
                rightPoint--;
            }
        }
        if (left < leftPoint) {
            //左边
            quicklySort(arr, left, leftPoint - 1);
        }
        if (leftPoint < right) {
            //右边
            quicklySort(arr, leftPoint + 1, right);
        }

    }


    /**
     * 堆排序
     * @param arr
     */
    private static void heapSort(int[] arr) {
        int len = arr.length;
        /**
         * 数组构造堆树，从倒数第一个非叶子结点开始逆向一次进行堆化操作。最后一个叶子结点的父结点就是最后一个非叶子结点。
         * 索引从0开始。两个子结点索引是2i+1和2i+2,所以最后一个非叶子结点的索引就是 len/2 - 1
         */
        for (int i = len / 2 - 1; i >=0 ; i--) { //时间复杂度nlogn
            createMaxHeap(arr, i, len);
        }
        for (int i = len - 1; i > 0 ; i--) { //时间复杂度nlogn
            int maxData = arr[0]; //第一个数最大
            arr[0] = arr[i];
            arr[i] = maxData;
            /**
             * 交换第一个最后一个位置,然后重新构造大顶堆。每循环一次就构造好了一个数的位置，
             * 最后到i为止都是排好序的，堆化的时候不需要再操作了
             */
            createMaxHeap(arr, 0, i);
        }
    }
    /**
     * 大顶堆构造及堆化过程
     * @param arr
     * @param start  
     * @param end  end之后是已经排好序的，所以需要end下标来判断截止
     */
    private static void createMaxHeap(int[] arr, int start, int end) {
        int parentIndex = start;
        int leftChildIndex = 2 * parentIndex + 1;
        while (leftChildIndex < end) {
            int tempIndex = leftChildIndex;
            //比较左右结点谁大，记录谁的下标
            if (leftChildIndex + 1 < end && arr[leftChildIndex] < arr[leftChildIndex + 1]) {
                tempIndex = leftChildIndex + 1;
            }
            //父结点比孩子大，不交换
            if (arr[parentIndex] > arr[tempIndex]) {
                return;
            }
            else {
                //交换数据，刷新父结点继续执行堆化操作
                int tempData = arr[parentIndex];
                arr[parentIndex] = arr[tempIndex];
                arr[tempIndex] = tempData;
                parentIndex = tempIndex;
                leftChildIndex = 2 * parentIndex + 1;
            }
        }
    }
}
