package com.nijunyang.algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author: create by nijunyang
 * @date:2019/7/28
 */
public final class TreeUtil {

    private TreeUtil() {
    }


    /**
     * 构造二叉树
     * @param dataList
     * @param <T>
     * @return
     */
    public static <T> TreeNode<T> createBinaryTree(LinkedList<T> dataList) {
        TreeNode<T> node = null;
        if (dataList == null || dataList.isEmpty()) {
            return null;
        }
        T data = dataList.removeFirst();
        if (data != null) {
            node = new TreeNode(data);
            node.setLeftChild((createBinaryTree(dataList)));
            node.setRightChild((createBinaryTree(dataList)));
        }
        return node;
    }

    /**
     * 前序遍历 根 左子树 右子树
     * @param node
     */
    public static<N extends TreeNode<T>, T> void preOrderTraversal(N node) {
        if(node == null){
            return;
        }
        //遇根先输出，再去找左右
        System.out.print(node.getData());
        preOrderTraversal(node.getLeftChild());
        preOrderTraversal(node.getRightChild());
    }

    /**
     * 二叉树中序遍历 左子树 根 右子树
     * @param node   二叉树节点
     */
    public static<N extends TreeNode<T>, T> void inOrderTraversal(N node){
        if(node == null){
            return;
        }
        //先找左再输出根,再去找右
        inOrderTraversal(node.getLeftChild());
        System.out.print(node.getData());
        inOrderTraversal(node.getRightChild());
    }

    /**
     * 二叉树后序遍历  左子树 右子树 根
     * @param node   二叉树节点
     */
    public static<N extends TreeNode<T>, T> void postOrderTraversal(N node){
        if(node == null){
            return;
        }
        //先找左右，最后输出根
        postOrderTraversal(node.getLeftChild());
        postOrderTraversal(node.getRightChild());
        System.out.print(node.getData());
    }

    /**
     * 利用栈前序遍历二叉树
     * @param root
     */
    public static <N extends TreeNode<T>, T> void preOrderTraversalByStack(N root) {
        Stack<TreeNode<T>> stack = new Stack<>();
        TreeNode<T> node = root;
        while(node != null || !stack.isEmpty()) {
            //节点不为空，遍历节点，并入栈用于回溯
            while(node != null) {
                System.out.print(node.getData());
                stack.push(node);
                node = node.getLeftChild();
            }
            //没有左节点，弹出该栈顶节点（回溯），访问右节点
            if(!stack.isEmpty()) {
                node = stack.pop();
                node = node.getRightChild();
            }
        }
    }

    /**
     * 层次遍历
     * @param root
     * @param <T>
     */
    public static <N extends TreeNode<T>, T> void levelOrder(N root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);  //入队
        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.poll(); //取出
            if (node != null) {
                System.out.print(node.getData());
                queue.offer(node.getLeftChild());   //左孩子入队
                queue.offer(node.getRightChild());  //右孩子入队
            }
        }
    }
}
