package leetcode.链表;

public class _83_RemoveDuplicatesfromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head;
        ListNode back = head.next;
        while (back != null) {
            if (pre.val == back.val) {
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
