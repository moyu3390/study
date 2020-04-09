package com.nijunyang.algorithm.queue;

/**
 * Description:
 * Created by nijunyang on 2020/4/4 21:44
 */
public class MyQueue<E> {
    private static final int DEFAULT_SIZE = 10;
    private Object[] elements;
    private int headIndex;
    private int tailIndex;
    private int capacity; //从容量
    private int size;  //已放元素个数

    public MyQueue() {
        this(DEFAULT_SIZE);
    }

    public MyQueue(int capacity) {
        this.elements = new Object[capacity];
        this.capacity = capacity;

    }

    public void push(E e){
        if (isFull()) {  //插入先判断是否满
            return;
        }
        elements[tailIndex] = e;
        size++;
        tailIndex = (tailIndex + 1) % capacity;  //取模实现循环的操作
    }

    public E pop(){
        if(isEmpty()) {  //取元素先判空
            return null;
        }
        E e = (E) elements[headIndex];
        headIndex = (headIndex + 1) % capacity; //取模实现循环的操作
        size--;
        return e;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public int size(){
        return this.size;
    }

    public int getCapacity(){
        return this.capacity;
    }

    public boolean isFull(){
        return this.size == this.capacity;
    }

    public static void main(String[] args) {
        MyQueue<Integer> myQueue = new MyQueue(3);
        myQueue.push(1);
        System.out.println(myQueue.size());
        myQueue.push(2);
        myQueue.push(3);
        System.out.println(myQueue.pop());
        System.out.println(myQueue.size());
        myQueue.push(4);
        myQueue.push(5);
        System.out.println(myQueue.size());

    }


}
