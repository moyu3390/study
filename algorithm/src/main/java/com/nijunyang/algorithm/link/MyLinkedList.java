package com.nijunyang.algorithm.link;

/**
 * Description:
 * Created by nijunyang on 2020/3/31 22:09
 */
public class MyLinkedList<E> {
    private Node<E> head;
    private int size = 0;

    /**
     * 头部插入O(1)
     * @param data
     */
    public void insertHead(E data){
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void insert(E data,int position){
        if(position == 0) {
            insertHead(data);
        }else{
            Node cur = head;
            for(int i = 1; i < position ; i++){
                cur = cur.next;		//一直往后遍历
            }
            Node newNode = new Node(data);
            //
            newNode.next = cur.next;		//新加的点指向后面 保证不断链
            cur.next = newNode;			//把当前的点指向新加的点
            size++;
        }

    }


    public void deleteHead(){
        head = head.next;
        size--;
    }

    public void delete(int position){
        if(position == 0) {
            deleteHead();
        }else{
            Node cur = head;
            for(int i = 1; i < position ; i ++){
                cur = cur.next;  //找到删除位置的前一个结点
            }
            cur.next = cur.next.next; //cur.next 表示的是删除的点，后一个next就是我们要指向的
            size--;
        }

    }

    public int size( ){
        return size;
    }

    public String toString( ){
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Node<E> node = head;
        sb.append(node.value);
        int counter = 0;
        for (;;) {
            if (++counter == size) {
                break;
            }
            sb.append(",");
            node = node.next;
            sb.append(node.value);


        }
        sb.append(']');
        return sb.toString();
    }

    public static void main(String[] args) {
        MyLinkedList myList = new MyLinkedList();
        myList.insertHead(5);
        System.out.println(myList);
        myList.insertHead(7);
        System.out.println(myList);
        myList.insertHead(10);
        System.out.println(myList);
        myList.delete(0);
        System.out.println(myList);
        myList.deleteHead();
        System.out.println(myList);
        myList.insert(11, 1);
        System.out.println(myList);

    }

    private static class Node<E>{

        E value;		//值
        Node<E> next;	    //下一个的指针

        public Node() {
        }

        public Node(E value) {
            this.value = value;
        }
    }
}


