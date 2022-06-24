package leetcode.链表;

import DataStructure.stack.Stack;

public class _232_ImplementQueueUsingStacks {

    class MyQueue {

        Stack<Integer> inStack = new Stack<>();
        Stack<Integer> outStack = new Stack<>();

        public MyQueue() {

        }

        public void push(int x) {
            inStack.push(x);
        }

        public int pop() {
            checkOutStack();
            return outStack.pop();
        }

        public int peek() {
            checkOutStack();
            return outStack.peek();
        }

        public boolean empty() {
            return inStack.isEmpty() && outStack.isEmpty();
        }

        private void checkOutStack() {
            if (outStack.isEmpty()) {
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
            }
        }
    }

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
}
