package com.nijunyang.algorithm.tree;

/**
 * @author: create by nijunyang
 * @date:2019/7/28
 */
public class TreeNode<T> {
    private T data;
    private TreeNode leftChild;
    private TreeNode rightChild;

    public TreeNode() {
    }

    public TreeNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
    }
}
