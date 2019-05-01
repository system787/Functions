package com.hoang.vincent.data.structures;

public class LinkedList<E> {

    private Node<E> mHead;
    private Node<E> mTail;

    private int mSize;

    public LinkedList() {
        this.mHead = null;
        this.mTail = null;
        this.mSize = 0;
    }

    public LinkedList(E data) {
        Node<E> node = new Node<E>(data);
        setHead(node);
        setTail(node);
        setSize(1);
    }

    protected Node<E> getHead() {
        return mHead;
    }

    protected void setHead(Node<E> head) {
        mHead = head;
    }

    protected Node<E> getTail() {
        return mTail;
    }

    protected void setTail(Node<E> tail) {
        mTail = tail;
    }

    protected Node<E> getNode(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException();
        }

        // index = heads or tails
        if (index == 0) {
            return getHead();
        } else if (index == getSize() - 1) {
            return getTail();
        }

        // iterate to index
        Node<E> node = getHead().getNext();
        for (int i = 1; i < index; i++) {
            node = node.getNext();
        }

        return node;
    }

    public int getSize() {
        return mSize;
    }

    protected void setSize(int size) {
        mSize = size;
    }

    public boolean isEmpty() {
        return mSize == 0;
    }

    public void add(int index, E data) throws IndexOutOfBoundsException {

        if (index < 0 || index > getSize()) {
            throw new IndexOutOfBoundsException();
        }

        // appending
        if (index == getSize()) {
            Node<E> node = new Node<>(data, getTail(), null);

            if (isEmpty()) {
                setHead(node);
            } else {
                getTail().setNext(node);
            }

            setTail(node);
        }

        // inserting
        else {
            Node<E> currentNode = getNode(index);
            Node<E> newNode = new Node<>(data, currentNode.getPrev(), currentNode);

            if (index != 0) {
                currentNode.getPrev().setNext(newNode);
            }

            currentNode.setPrev(newNode);

            if (index == 0) {
                setHead(newNode);
            }
        }

        // increase list size
        setSize(getSize() + 1);
    }

    public void insertFront(E data) {
        add(0, data);
    }

    public void insertEnd(E data) {
        add(getSize(), data);
    }

    /**
     * remove()
     *
     * removes a node and returns data found at removed node
     *
     * @param index to remove element at
     * @return data found at index
     * @throws IndexOutOfBoundsException if index exists outside of list size
     */
    public E remove(int index) throws IndexOutOfBoundsException {
        Node<E> node = getNode(index);

        // list size 1
        if (getSize() == 1) {
            setHead(null);
            setTail(null);
        }

        // list size > 1

        // remove head
        else if (index == 0) {
            node.getNext().setPrev(null);
            setHead(node.getNext());
        }

        // remove tail
        else if (index == getSize() - 1) {
            node.getPrev().setNext(null);
            setTail(node.getPrev());
        }

        // remove inner
        else {
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
        }

        // decrease list size
        setSize(getSize() - 1);

        return node.getData();
    }

    public E set(int index, E data) throws IndexOutOfBoundsException {
        Node<E> node = getNode(index);
        E currentNodeData = node.getData();

        node.setData(data);

        return currentNodeData;
    }

    public void clearList() {
        while (!isEmpty()) {
            remove(0);
        }
    }

    public LinkedList<E> clone() {
        LinkedList<E> clonedList = new LinkedList<>();

        Node<E> node = getHead();

        while (node != null) {
            clonedList.insertEnd(node.getData());
            node = node.getNext();
        }

        return clonedList;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < getSize() - 1; i++) {
            sb.append(getNode(i).getData()).append(" ");
        }

        return sb.toString().trim();
    }

    /**
     * Node class
     */
    protected static class Node<E> {
        E mData;
        Node<E> mPrev;
        Node<E> mNext;

        public Node(E data) {
            mData = data;
            mNext = null;
            mPrev = null;
        }

        public Node(E data, Node<E> prev, Node<E> next) {
            mData = data;
            mPrev = prev;
            mNext = next;
        }

        public Node() {
            this(null);
        }

        public E getData() {
            return mData;
        }

        public void setData(E data) {
            mData = data;
        }

        public Node<E> getNext() {
            return mNext;
        }

        public void setNext(Node<E> next) {
            mNext = next;
        }

        public Node<E> getPrev() {
            return mPrev;
        }

        public void setPrev(Node<E> prev) {
            mPrev = prev;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object obj) {
            Node<E> node = (Node<E>) obj;

            if (node == null) {
                return false;
            }

            if (this == node) {
                return true;
            }

            if (this.getClass() != node.getClass()) {
                return false;
            }

            if (this.getData() == node.getData() && this.getPrev() == node.getPrev() && this.getNext() == node.getNext()) {
                return true;
            }

            return false;
        }
    }
}
