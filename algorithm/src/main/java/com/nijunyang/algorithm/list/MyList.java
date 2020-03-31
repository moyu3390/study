package com.nijunyang.algorithm.list;

/**
 * Description:
 * Created by nijunyang on 2020/3/30 23:25
 */
public interface MyList<E> {
    public void add(E element);

    public void remove(int index);

    public E get(int index);

    public int size();

}
