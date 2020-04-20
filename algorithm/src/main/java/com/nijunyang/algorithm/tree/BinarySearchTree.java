package com.nijunyang.algorithm.tree;

import com.nijunyang.algorithm.util.RefObject;

/**
 * Description:
 * Created by nijunyang on 2020/4/19 20:03
 */
public class BinarySearchTree<T extends Comparable<T>> extends TreeNode<T> {
    private T data;
    private BinarySearchTree<T> leftChild;
    private BinarySearchTree<T> rightChild;

    public BinarySearchTree(T data) {
        this.data = data;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree(5);
        BinarySearchTree.insert(binarySearchTree, 3);
        BinarySearchTree.insert(binarySearchTree, 1);
        BinarySearchTree.insert(binarySearchTree, 4);
        BinarySearchTree.insert(binarySearchTree, 8);
        BinarySearchTree.insert(binarySearchTree, 7);
        BinarySearchTree.insert(binarySearchTree, 9);
        BinarySearchTree.insert(binarySearchTree, 10);
        BinarySearchTree.insert(binarySearchTree, 0);
        TreeUtil.inOrderTraversal(binarySearchTree);
        System.out.println();
        BinarySearchTree<Integer> integerBinarySearchTree = BinarySearchTree.find(binarySearchTree, 9, new RefObject<>());
        delete(binarySearchTree, 8);
        delete(binarySearchTree, 7);
        TreeUtil.inOrderTraversal(binarySearchTree);
//        System.out.println(integerBinarySearchTree.data);
    }

    /**
     * 插入数据
     * @param root
     * @param data
     * @param <T>
     */
    public static <T extends Comparable<T>> void insert(BinarySearchTree<T> root, T data) {
        if (root.data.compareTo(data) < 0) {
            if (root.rightChild == null) {
                root.rightChild = new BinarySearchTree(data);
            } else {
                insert(root.rightChild, data);
            }
        } else {
            if (root.leftChild == null) {
                root.leftChild = new BinarySearchTree(data);
            } else {
                insert(root.leftChild, data);
            }
        }
    }


    /**
     * 查询数据
     * @param root
     * @param data
     * @param parent   用于带出父节点
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> BinarySearchTree<T> find(
            BinarySearchTree<T> root, T data, RefObject<BinarySearchTree<T>> parent) {
        if (root.data.compareTo(data) == 0) {
            return root;
        }
        parent.setValue(root);
        if (root.data.compareTo(data) < 0) {
            if (root.rightChild != null) {
                return find(root.rightChild, data, parent);
            }
            return null;
        } else {
            if (root.leftChild != null) {
                return find(root.leftChild, data, parent);
            }
            return null;
        }
    }

    /**
     * 查询最大数据
     * @param root
     * @param parentRef  最大结点的父结点引用
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> BinarySearchTree<T> findMax(
            BinarySearchTree<T> root, RefObject<BinarySearchTree<T>> parentRef) {
        if (root.rightChild != null) {
            parentRef.setValue(root);
            return findMax(root.rightChild, parentRef);
        }
        return root;
    }

    /**
     * 查询最小数据
     * @param root
     * @param parentRef 最小结点的父结点引用
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> BinarySearchTree<T> findMin(
            BinarySearchTree<T> root, RefObject<BinarySearchTree<T>> parentRef) {
        if (root.leftChild != null) {
            parentRef.setValue(root);
            return findMin(root.leftChild, parentRef);
        }
        return root;
    }

    /**
     * 删除数据
     * @param root
     * @param data
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> boolean delete(BinarySearchTree<T> root, T data) {

        RefObject<BinarySearchTree<T>> parentRef = new RefObject<>();
        BinarySearchTree<T> delBinarySearchTree = find(root, data, parentRef);
        if (delBinarySearchTree == null) {
            return false;
        }
        /**
         * 二叉搜索树结点的删除分三种情况：
         * 1.叶子结点，可以直接删除
         * 2.度为1的结点，可以直接删除（只有一个子树的结点）
         * 3.两棵子树的结点删除，找前驱结点/后继结点。就是删除了该结点，前驱/后继结点可以直接补位
         * 如果删除的根左边的结点，那么就是找前驱结点，前驱结点是其左子树中最大的结点，前驱结点的右子树一定为空，因为没有比它大的了
         * 如果删除的根右边的结点，那么就是找后继结点，后继结点是其右子树中最小的结点，后继结点的左子树一定为空，因为没有比它小的了
         * 如此一来，可以看出找到的前驱/后继结点的条件肯定是满足1或者2的
         */
        BinarySearchTree<T> parent = parentRef.getValue();
        //叶子结点直接将父结点的孩子置空
        if (delBinarySearchTree.leftChild == null && delBinarySearchTree.rightChild == null) {
            if (parent.rightChild == delBinarySearchTree) {
                parent.rightChild = null;
            } else {
                parent.leftChild = null;
            }
        }
        //度为2的结点删除
        else if (delBinarySearchTree.leftChild != null && delBinarySearchTree.rightChild != null) {
            //删除比根大的，删除的结点在根的右边，需要找后继结点
            if (root.data.compareTo(data) < 0) {
                RefObject<BinarySearchTree<T>> postParentRef = new RefObject<>(); //后继结点的父结点
                BinarySearchTree<T> postNode = findMin(root.rightChild, postParentRef);
                //判断要删除的结点是它父结点的左结点还是右结点，修改对应指针指向后继结点
                if (parent.data.compareTo(delBinarySearchTree.data) < 0) {
                    parent.rightChild = postNode;
                } else {
                    parent.leftChild = postNode;
                }
                postParentRef.getValue().leftChild = null; //后继结点因为要移走，所以置空其父结点的左孩子（后继必定是其父的左孩子）
                if (postNode.rightChild != null) {//后继结点的左子树一定为空, 将其父结点的左孩子指向后继结点的右孩子
                    postParentRef.getValue().leftChild = postNode.rightChild;
                    postNode.rightChild = null; //置空相关引用，便于垃圾回收
                }
                //将删除的这个结点的左右子树的指针给到后继结点
                postNode.rightChild = delBinarySearchTree.rightChild;
                delBinarySearchTree.rightChild = null; //置空相关引用，便于垃圾回收
                postNode.leftChild = delBinarySearchTree.leftChild;
                delBinarySearchTree.leftChild = null; //置空相关引用，便于垃圾回收
            }
            //删除根或者比根小的，删除的结点在根的左边，需要找前驱结点
            else{
                RefObject<BinarySearchTree<T>> preParentRef = new RefObject<>(); //前驱结点的父结点
                BinarySearchTree<T> preNode = findMax(root.leftChild, preParentRef);

                if (parent != null) { //如果删除的是根结点 没有父结点
                    //判断要删除的结点是它父结点的左结点还是右结点，修改对应指针指向前驱结点
                    if (parent.data.compareTo(delBinarySearchTree.data) < 0) {
                        parent.rightChild = preNode;
                    } else {
                        parent.leftChild = preNode;
                    }
                }
                preParentRef.getValue().rightChild = null; //前驱结点因为要移走，所以置空其父结点的右孩子（前驱必定是其父的右孩子）
                if (preNode.leftChild != null) {//前驱结点的右子树一定为空, 将其父结点的右孩子指向前驱结点的左孩子
                    preParentRef.getValue().rightChild = preNode.leftChild;
                    preNode.leftChild = null; //置空相关引用，便于垃圾回收
                }

                if (delBinarySearchTree == root) {
                    //删除的是根 直接将交换值
                    delBinarySearchTree.data = preNode.data;
                }
                else {
                    //将删除的这个结点的左右孩子的指针给到前驱结点
                    preNode.rightChild = delBinarySearchTree.rightChild;
                    delBinarySearchTree.rightChild = null; //置空相关引用，便于垃圾回收
                    preNode.leftChild = delBinarySearchTree.leftChild;
                    delBinarySearchTree.leftChild = null; //置空相关引用，便于垃圾回收
                }
            }
        }
        //度为1的结点删除 将其父结点的孩子指向它的孩子
        else {
            BinarySearchTree<T> leftChild = delBinarySearchTree.leftChild;
            BinarySearchTree<T> child =  leftChild == null ? delBinarySearchTree.rightChild : leftChild;
            delBinarySearchTree.leftChild = null;  //置空相关引用，便于垃圾回收
            delBinarySearchTree.rightChild = null; //置空相关引用，便于垃圾回收
            if (parent.data.compareTo(child.data) < 0) {
                parent.rightChild = child;
            } else {
                parent.leftChild = child;
            }
        }
        return true;
    }


    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public BinarySearchTree<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinarySearchTree<T> leftChild) {
        this.leftChild = leftChild;
    }

    @Override
    public BinarySearchTree<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinarySearchTree<T> rightChild) {
        this.rightChild = rightChild;
    }
}
