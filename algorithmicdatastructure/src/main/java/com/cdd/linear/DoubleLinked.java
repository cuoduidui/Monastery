package com.cdd.linear;

import java.util.Date;

/**
 * 双向链表 队列 先进先出 FIFO
 *
 * @author yangfengshan
 * @create 2019-06-25 18:24
 **/
public class DoubleLinked<T> {
    private Node<T> frist;
    private Node<T> end;

    public DoubleLinked() {
        frist = null;
    }

    public boolean push(T data) {
        //TODO
        if (frist == null) {
            frist = new Node<T>(end, data);
            return true;
        }
        return true;
    }

    public T pop() {
        T data = frist.data;
        frist = frist.next;
        return frist.data;
    }

    class Node<T> {
        Node next;
        T data;

        public Node() {

        }

        public Node(Node next, T data) {
            this.data = data;
            this.next = next;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

}
