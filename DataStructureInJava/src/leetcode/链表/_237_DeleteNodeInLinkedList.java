package leetcode.链表;

/**
 * https://leetcode.cn/problems/delete-node-in-a-linked-list/
 */
public class _237_DeleteNodeInLinkedList {






    public void deleteNode(ListNode node) {
          node.val = node.next.val;
          node.next = node.next.next;
    }
}
