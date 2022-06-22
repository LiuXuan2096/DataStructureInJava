package leetcode.链表;

/**
 * https://leetcode.cn/problems/reverse-linked-list/
 */

public class _206_ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        // recursively
//        if (head == null || head.next == null) {
//            return null;
//        }
//        ListNode newHead = reverseList(head.next);
//        head.next.next = head;
//        head.next = null;
//        return newHead;
        // iteratively
        ListNode newHead = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        return newHead;
    }
}
