package com.nijunyang.algorithm.stack;

/**
 * Description:
 * Created by nijunyang on 2020/4/1 23:48
 */
public class MyStack<E> {

    private static final int DEFAULT_SIZE = 10;

    private Object[] elements;

    private int size;

    public MyStack() {
        this(DEFAULT_SIZE);
    }

    public MyStack(int capacity) {
        this.elements = new Object[capacity];
    }

    /**
     * 入栈
     * @param e
     */
    public void push(E e) {
        //弹性伸缩，扩容/收缩释放内存空间
        if (size >= elements.length) {
            resize(size * 2);
        } else if (size > 0 && size < elements.length / 2) {
            resize(elements.length / 2);
        }
        elements[size++] = e;
    }

    /**
     * 出栈
     */
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E e = (E) elements[--size];   //size是5，那么最后一个元素就是4也就是--size
        elements[size] = null;        //现在size已经是4了，弹出就是4这个元素的位置置为空
        return e;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /**
     * 扩容/收缩
     */
    private void resize(int newCapacity) {
        Object[] temp =  new Object[newCapacity];
        for(int i = 0 ; i < size; i ++){
            temp[i] = elements[i];
        }
        elements = temp;
    }

    public static void main(String[] args){
        MyStack<Integer> myStack = new MyStack(5);
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        myStack.push(6);
        System.out.println(myStack.size);
        Integer e = myStack.pop();
        System.out.println(e);
        e = myStack.pop();
        System.out.println(e);
        e = myStack.pop();
        System.out.println(e);
        e = myStack.pop();
        System.out.println(e);
        e = myStack.pop();
        System.out.println(e);
        e = myStack.pop();
        System.out.println(e);
        e = myStack.pop();
        System.out.println(e);
    }


}
