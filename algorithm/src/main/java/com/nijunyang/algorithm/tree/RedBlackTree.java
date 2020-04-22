package com.nijunyang.algorithm.tree;

/**
 * Description: 红黑树
 * Created by nijunyang on 2020/4/22 15:23
 *
 * 红黑树的性质：
 *         1.每个结点不是红色就是黑色
 *         2.不可能有连在一起的红色结点（黑色的就可以），每个叶子节点都是黑色的空节点（nil），也就是说，叶子节点不存储数据
 *         3.根结点都是黑色 root
 *         4.每个节点，从该节点到达其可达叶子节点的所有路径，都包含相同数目的黑色节点
 *         5.新插入的元素都是红色，根除外
 */

public class RedBlackTree {

    private Node root = Node.nil;

    public <T extends Comparable<T>> void insert(T data){
        Node<T> temp = root;
        Node<T> node = new Node<>(data);
        if (root == null) {
            root = node;
        }
        else {
            node.black = false;
            //插入
            while (true) {
                if (temp.data.compareTo(data) < 0) {
                    if (temp.rightChild == Node.nil) {
                        temp.rightChild = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.rightChild;
                    }
                }
                else if (temp.data.compareTo(data) == 0) {
                    //等于保留原来数据
                    return;
                }
                else {
                    if (temp.leftChild == Node.nil) {
                        temp.leftChild = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.leftChild;
                    }
                }
            }

            //变色和旋转
            fixTree(node);

        }

    }

    /**
     * 变色和旋转
     * @param node
     * @param <T>
     */
    private <T extends Comparable<T>> void fixTree(Node<T> node) {
        /**
         * 1.变色 条件：父结点及叔叔结点都是红色，变色过程：把父结点和叔叔结点都变成黑色，把爷爷设置成红色，指针指向爷爷结点
         * 2.左旋：上一步将指针指向了爷爷结点.条件：当前结点（爷爷结点）父结点是红色，叔叔是黑色，且当前结点是右子树。进行左旋：
         * 临时指针指向父结点，将其父结点（指针变化前的爷爷）变成当前结点的左子树，将其原来的左子树变成其原来父结点的右子树，
         *
         * 3.右旋：上一步将指针指向了父结点，条件：当前结点（父结点，相对左旋来说）父结点是红色，叔叔是黑色，且当前结点是左子树。进行右旋：
         * 父结点变成黑色，爷爷变成红色，以爷爷为点右旋。指针指向爷爷结点。将当前结点（爷爷结点）的父结点变成其右子树，其右子树变成其父的左子树
         *
         * 总的来说 这里面几个关键操作结点指针变化，
         * 首先进来先判断是否需要变色，当前操作结点就是新插入的这个元素的结点，变色完成之后，将操作结点变成其爷爷结点
         * 左旋是基于新的这个操作结点进行操作，左旋完成之后又将操作结点变成它的父亲结点
         * 右旋又是基于左旋后更新了的操作结点进行操作
         */
        Node<T> currentNode = node;
        while (!currentNode.parent.black) {
            Node<T> temp = Node.nil;
            if (currentNode.parent == currentNode.parent.parent.leftChild) { //当前父结点是左孩子
                temp = currentNode.parent.parent.rightChild; //叔叔结点
                //变色
                if (temp != Node.nil && !temp.black) { //叔叔也是红色，将父和叔叔都变黑色
                    currentNode.parent.black = true;
                    temp.black = true;
                    currentNode.parent.parent.black = false; //爷爷变成红色
                    currentNode = currentNode.parent.parent; //变色完成指向爷爷
                    continue;  //进入下一次循环判断爷爷的位置是否也需要变色，直到不变满足变色了才开始左旋/右旋
                }
                if (currentNode == currentNode.parent.rightChild) { //当前结点是右子树
                    currentNode = currentNode.parent; //以其父结点进行左旋
                    leftRotate(currentNode);
                }

            }
            else { //当前父结点是右孩子

            }


        }
    }

    /**
     * 左旋：将其父结点变成当前结点的左子树，将其原来的左子树变成其原来父结点的右子树
     * @param currentNode
     * @param <T>
     */
    private <T extends Comparable<T>> void leftRotate(Node<T> currentNode) {
        if (currentNode.parent != Node.nil) {
            if (currentNode == currentNode.parent.leftChild) {
                currentNode.parent.leftChild = currentNode.rightChild;
            }
            else {
                currentNode.parent.rightChild = currentNode.rightChild;
            }

            currentNode.rightChild.parent = currentNode.parent;
            currentNode.parent = currentNode.rightChild;
            if (currentNode.rightChild.leftChild != Node.nil) {
                currentNode.rightChild.leftChild.parent = currentNode;
            }
            currentNode.rightChild = currentNode.rightChild.leftChild;
            currentNode.parent.leftChild = currentNode;
        }
        else { //根结点
            Node right = root.rightChild;
            root.rightChild = right.leftChild;
            right.leftChild.parent = root;
            root.parent = right;
            right.leftChild = root;
            right.parent = Node.nil;
            root = right;
        }
    }


    private static class Node<T extends Comparable<T>> extends TreeNode<T> {
        private static final Node nil = new Node<>(null);
        T data;
        Node<T> parent;
        Node<T> leftChild = nil;
        Node<T> rightChild = nil;
        boolean black = true; //默认黑色

        public Node(T data) {
            this.data = data;
        }
    }
}
