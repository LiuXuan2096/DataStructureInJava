package DataStructure.tree;

import java.util.Comparator;

public class BST<E> extends BinaryTree<E> {

    private Comparator<E> comparator;

    public BST() {
        this(null);
    }

    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void add(E element) {
        elementNotNullCheck(element);

        // 添加的是第一个节点
        if (root == null) {
            Node<E> node = new Node<>(element, null);
            root = node;
            size++;
            return;
        }

        // 说明添加到不是第一个节点
        // 找到父节点
        Node<E> parent = root;
        Node<E> node = root;
        int cmp = 0;
        do {
            cmp = compare(element, node.element);
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                node.element = element;
                return;
            }
        } while (node != null);

        // 根据cmp决定插入到父节点的哪个位置
        Node<E> newNode = new Node(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
    }

    public void remove(E element) {
        remove(node(element));
    }

    public boolean contains(E element) {
        return node(element) != null;
    }

    private void remove(Node<E> node) {
        if (node == null) return;

        size--;

        if (node.hasTwoChildren()) { // 要删的是度为2的节点
            Node<E> s = sucessor(node); // 找到后继节点
            node.element = s.element;   // 用后继节点的值覆盖要删除的节点的值
            node = s; // 删除后继节点
        }

        // 删除node节点 此时node的节点的值必然为0或者1
        Node<E> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) { // node是度为1的节点
            // 更改parent
            replacement.parents = node.parents;
            // 更改parent的left right的指向
            if (node.parents == null) { // node是度为1的节点并且是根节点
                root = replacement;
            } else if (node == node.parents.left) {
                replacement.parents.left = replacement;
            } else {
                replacement.parents.right = replacement;
            }
        } else if (node.parents == null) { //node是根节点并且是叶子节点
                root = null;
         } else {
            // node是叶子节点 但不是根节点
            if (node == node.parents.left) {
                node.parents.left = null;
            } else {
                node.parents.right = null;
            }
        }
    }

    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp > 0) {
                node = node.left;
            } else if (cmp < 0) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    /**
     * 返回值等于0，代表e1和e2相等；返回值大于0，代表e1大于e2；返回值小于于0，代表e1小于e2
     * @param e1
     * @param e2
     * @return
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }
}
