package com.hoang.vincent.data.structures;

public class Stack<E> {

    private LinkedList<E> mList;

    public Stack() {
        mList = new LinkedList<>();
    }

    protected LinkedList<E> getList() {
        return mList;
    }

    protected void setList(LinkedList<E> list) {
        mList = list;
    }

    public boolean isEmpty() {
        return getList().isEmpty();
    }

    public E peek() {
        return getList().getNode(0).getData();
    }

    public void push(E data) {
        getList().insertFront(data);
    }

    public E pop() {
        return getList().remove(0);
    }

    @Override
    public String toString() {
        return getList().toString();
    }

    public void clear() {
        getList().clearList();
    }
}
