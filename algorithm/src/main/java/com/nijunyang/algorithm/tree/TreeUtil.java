package com.nijunyang.algorithm.tree;

import java.util.LinkedList;
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
        TreeNode node = null;
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
     * 前序遍历 跟 左子树 右子树
     * @param node
     */
    public static void preOrderTraversal(TreeNode node) {
        if(node == null){
            return;
        }
        System.out.println(node.getData());
        preOrderTraversal(node.getLeftChild());
        preOrderTraversal(node.getRightChild());
    }

    /**
     * 二叉树中序遍历 左子树 根 右子树
     * @param node   二叉树节点
     */
    public static void inOrderTraversal(TreeNode node){
        if(node == null){
            return;
        }
        inOrderTraversal(node.getLeftChild());
        System.out.println(node.getData());
        inOrderTraversal(node.getRightChild());
    }

    /**
     * 二叉树后序遍历  左子树 右子树 根
     * @param node   二叉树节点
     */
    public static void postOrderTraversal(TreeNode node){
        if(node == null){
            return;
        }
        postOrderTraversal(node.getLeftChild());
        postOrderTraversal(node.getRightChild());
        System.out.println(node.getData());
    }

    /**
     * 利用栈遍历二叉树
     * @param root
     */
    public static void preOrderTraversalByStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while(node != null || !stack.isEmpty()) {
            //节点不为空，遍历节点，并入栈用于回溯
            while(node != null) {
                System.out.println(node.getData());
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
}
