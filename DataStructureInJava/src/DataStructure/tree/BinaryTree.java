package DataStructure.tree;

import leetcode.二叉树.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<E> {

    protected int size;
    protected Node<E> root;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public void preorder(Visitor<E> visitor) {
        if (visitor == null) return;
        preorder(root, visitor);
    }

    public void preorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop == true) {
            return;
        }
        visitor.stop = visitor.visit(node.element);
        preorder(node.left, visitor);
        preorder(node.right, visitor);
    }

    public void inorder(Visitor<E> visitor) {
        if (visitor == null) return;
        inorder(root, visitor);
    }

    public void inorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        inorder(node.left, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        inorder(node.right, visitor);
    }

    public void postorder(Visitor<E> visitor) {
        if (visitor == null) return;
        postorder(root, visitor);
    }

    public void postorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        postorder(node.left, visitor);
        if (visitor.stop) return;
        postorder(node.right, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
    }

    public void levelOrder(Visitor<E> visitor) {
        if (root == null || visitor.stop) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            visitor.stop = visitor.visit(node.element);
            if (visitor.stop) return;

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * 按照层序遍历，不满足二叉树是完全二叉树的情况有：
     * 1. 某个节点左子树为空 右子树不为空
     * 2. 第一次遇到某个节点右子树为空的情况时，后续遍历的节点不是叶子节点
     * @return
     */
    public boolean isComplete() {
        if (root == null) return false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        boolean isLeaf = false;

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (isLeaf && !node.isLeaf()) return false;
            if (node.left == null && node.right != null) return false;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            } else {
                isLeaf = true; // 左子树不为空，右子树为空时，此后遍历的节点都应为叶子节点
            }
        }
        return true;
    }

    public int height() {
        if (root == null) return 0;

        int height = 0; // 树的高度
        int levelSize = 1; // 每一层的元素数量
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            levelSize--; // 有元素出队 元素数量减一

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (levelSize == 0) { // 说明即将要访问下一层
                levelSize = queue.size(); // 访问下一层前更新levelSize
                height++;
            }
        }
        return height;
    }

    public int height2() {
        return height(root);
    }

    private int height(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    protected Node<E> predecessor(Node<E> node) {
        if (node == null) return null;

        Node<E> p = node.left;
        if (p != null) { // // 前驱节点在左子树中
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        // 前驱节点在父节点或祖父节点中
        while (node.parents != null && node == node.parents.left) {
            node = node.parents;
        }

        // while循环结束时如果 node.parents == null 说明node无前驱节点
        //               如果 node为右子树 则前驱节点为node.parents
        return node.parents;
    }

    protected Node<E> sucessor(Node<E> node) {
        if (node == null) return null;

        // 后继节点在右子树当中
        Node<E> successor = node.right;
        if (successor != null) {
            while (successor.left != null) {
                successor = successor.left;
            }
            return successor;
        }

        // 后继节点在父节点或祖父节点中
        while (node.parents != null && node == node.parents.right) {
            node = node.parents;
        }

        // while循环结束时 如果node.parents == null 说明node一直是右子树无后继节点
        //                如果node为左子树 其后继节点为父节点
        return node.parents;
    }


    public static abstract class Visitor<E> {
        boolean stop;

        /**
         * 如果返回ture 表示停止遍历
         * @param element
         * @return
         */
        abstract boolean visit(E element);
    }

    protected static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parents;
        public Node(E element, Node parents) {
            this.element = element;
            this.parents = parents;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }
}
