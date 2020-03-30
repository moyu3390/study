package com.nijunyang.algorithm.list;


/**
 * Description:
 * Created by nijunyang on 2020/3/30 23:25
 */
public class MyArrayList<E>{

    private static final int DEFAULT_SIZE = 10;
    /**
     * 数据（数组存放）
     */
    private Object[] elements;
    /**
     * 当前存放到数组的index
     */
    private int currentIndex;
    /**
     * 总容量大小（数组长度）
     */
    private int size;

    public MyArrayList() {
        this.elements = new Object[DEFAULT_SIZE];
        this.size = DEFAULT_SIZE;
    }

    public MyArrayList(int size) {
        this.elements = new Object[size];
        this.size = size;
    }

    public void add(E element) {
        elements[currentIndex++] = element; //后++是先赋值了之后再++
        //放到最后就进行扩容 容量翻倍
        if (currentIndex == size) {
            this.size = this.size * 2 ;
            Object newData[] = new Object[this.size];
            for (int i = 0; i < elements.length; i++) {
                newData[i] = elements[i];
            }
            this.elements = newData;
        }
    }

    /**
     * 按索引移除
     * @param index
     */
    public void remove(int index) {
        if (index >= 0 && index < currentIndex) {
            //后面的元素来覆盖当前这个元素，然后后面的全部前移
            for (int j = index; j < this.elements.length - 1; j++) {
                elements[j] = elements[j + 1];
                //null 就后不用再移动了
                if (elements[j] == null) {
                    break;
                }
            }
            this.currentIndex--;
        }
    }

    public E get(int index) {
        if (index >= 0 && index < currentIndex) {
            return (E) this.elements[index];
        }
        return null;
    }

    public int size() {
        return currentIndex;
    }

    public String toString() {
        if (currentIndex == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < currentIndex; i++) {
            if (i == currentIndex - 1) {
                sb.append(elements[i].toString());
                sb.append(']');
                break;
            }
            sb.append(elements[i].toString());
            sb.append(',');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
    	MyArrayList<Integer> myArrayList = new MyArrayList<>(2);
        myArrayList.add(0);
        myArrayList.add(1);
        myArrayList.add(2);
        System.out.println(myArrayList.get(0));
        System.out.println(myArrayList.size());
        System.out.println(myArrayList);
        myArrayList.remove(1);
        System.out.println(myArrayList.get(0));
        System.out.println(myArrayList.size());
        System.out.println(myArrayList);
    }
}
