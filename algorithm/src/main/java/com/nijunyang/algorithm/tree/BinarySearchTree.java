package com.nijunyang.algorithm.tree;

/**
 * Description:
 * Created by nijunyang on 2020/4/19 20:03
 */
public class BinarySearchTree<T extends Comparable> {
    private T data;
    private BinarySearchTree<T> leftChild;
    private BinarySearchTree<T> rightChild;

    public BinarySearchTree(T data) {
        this.data = data;
    }

    public static<T extends Comparable> void insert(BinarySearchTree root, T data) {
        if (root.data.compareTo(data) < 0) {
            if (root.rightChild == null) {
                root.rightChild = new BinarySearchTree(data);
            }
            else {
                insert(root.rightChild, data);
            }
        }
        else {
            if (root.leftChild == null) {
                root.leftChild = new BinarySearchTree(data);
            } else {
                insert(root.leftChild, data);
            }
        }
    }



}
