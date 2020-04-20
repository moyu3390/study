package com.nijunyang.algorithm.tree;

/**
 * @author: create by nijunyang
 * @date:2019/7/28
 */
public class TreeNode<T> {
    protected T data;
    protected TreeNode<T> leftChild;
    protected TreeNode<T> rightChild;

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

    public TreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }
}
