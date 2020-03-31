package com.nijunyang.algorithm.link;

import java.util.ArrayList;

/**
 * Description:
 * Created by nijunyang on 2020/3/30 21:49
 */
public class Test {

    public static void main(String[] args){
        long start = System.currentTimeMillis();
        int result = yuesefuhuan_link(70866, 116922);
        long end = System.currentTimeMillis();
        System.out.println(result);
        System.out.println("链表耗时：" + (end - start));
        System.out.println("-------------------------");

        start = System.currentTimeMillis();
        result = yuesefuhuan_arr(70866, 116922);
        end = System.currentTimeMillis();
        System.out.println(result);
        System.out.println("数组耗时：" + (end - start));
    }

    /**
     * 数组约瑟夫环
     */
    public static int yuesefuhuan_arr(int n, int m) {
        int size = n;
        ArrayList<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        int index = 0;
        while (size > 1) {
            //取模可以回到起点
            index = (index + m - 1) % size;
            list.remove(index);
            size--;
        }
        return list.get(0);
    }


    /**
     * 循环链表约瑟夫环力扣超时
     * @param n
     * @param m
     * @return
     */
    public static int yuesefuhuan_link(int n, int m) {
        if (n == 1) {
            return n - 1;
        }

        Node<Integer> headNode = new Node<>(0);
        Node<Integer> currentNode = headNode;
        //尾结点
        Node<Integer> tailNode = headNode;

        for (int i = 1; i < n; i++) {
            Node<Integer> next = new Node<>(i);
            currentNode.next = next;
            currentNode = next;
            tailNode = currentNode;

        }
        //成环
        tailNode.next = headNode;

        //保证第一次进去的时候指向头结点
        Node<Integer> remove = tailNode;
        Node<Integer> preNode = tailNode;
        int counter = n;
        while (true) {
            for (int i = 0; i < m; i++) {
                //一直移除头结点则，前置结点不动
                if (m != 1) {
                    preNode = remove;
                }
                remove = remove.next;
            }
            preNode.next = remove.next;
            if (--counter == 1) {
                return preNode.value;
            }
        }
    }

    static class Node<E>{
        E value;
        Node next;
        public Node() {
        }
        public Node(E value) {
            this.value = value;
        }
    }
}
