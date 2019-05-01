package com.hoang.vincent.data.structures;

public class Queue<E> {

    private LinkedList<E> mList;

    public Queue() {
        mList = new LinkedList<>();
    }

    protected LinkedList<E> getList() {
        return mList;
    }

    protected void setList(LinkedList<E> list) {
        mList = list;
    }

    public void queue(E data) {
        getList().insertEnd(data);
    }

    public E dequeue() {
        return getList().remove(0);
    }

    public boolean isEmpty() {
        return getList().isEmpty();
    }

    public E peek() {
        return getList().getNode(0).getData();
    }

    @Override
    public String toString() {
        return getList().toString();
    }

    public void clear() {
        getList().clearList();
    }
}
