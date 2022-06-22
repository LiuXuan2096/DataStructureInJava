package leetcode.链表;

public class _203_RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        while (head.val == val) {
            head = head.next;
            if (head == null) {
                return head;
            }
        }
        ListNode pre = head;
        ListNode back = pre.next;
        while (back != null) {
            if (back.val == val) {
                pre.next = back.next;
                back = back.next;
            } else {
                pre = back;
                back = back.next;
            }
        }
        return head;
    }
}
