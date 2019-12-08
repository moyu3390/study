package com.nijunyang.algorithm.tree;

import java.util.Arrays;
import java.util.LinkedList;

import static com.nijunyang.algorithm.tree.TreeUtil.*;

/**
 * @author: create by nijunyang
 * @date:2019/7/28
 */
public class Test {
    public static void main(String[] args)
    {
        LinkedList<Integer> inputList = new LinkedList<Integer>(
                Arrays.asList(new Integer[]{3,2,9,null,null,10,null,null,8,null,4,}));
        TreeNode treeNode = createBinaryTree(inputList);
        System.out.println("前序遍历：");
        preOrderTraversal(treeNode);
        System.out.println("前序遍历Stack：");
        preOrderTraversalByStack(treeNode);
        System.out.println("-------------------------");
        System.out.println("中序遍历：");
        inOrderTraversal(treeNode);
        System.out.println("后序遍历：");
        postOrderTraversal(treeNode);
    }
}
