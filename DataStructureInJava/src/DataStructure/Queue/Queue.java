package DataStructure.Queue;

import DataStructure.linkedlist.LinkedList;

public class Queue<E> {
    private LinkedList<E> list = new LinkedList<>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void clear() {
        list.clear();
    }

    public void offer(E element) {
        list.add(element);
    }

    public void deQueue() {
        list.remove(0);
    }

    public E front() {
        return  list.get(0);
    }

}
