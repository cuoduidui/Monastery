package com.cdd.linear;

/**
 * 栈 先进 后出 filo
 *
 * @author yangfengshan
 * @create 2019-06-25 17:44
 **/
public class Stack<T> {
    private int top;
    private Object[] array;

    public Stack() {
        array = new Object[100];
    }

    public Stack(int size) {
        array = new Object[size];
    }

    T push(T data) throws Exception {
        if (top >= array.length) throw new Exception("栈满了");
        array[top++] = data;
        return data;
    }

    T pop() throws Exception {
        if (top < 1) throw new Exception("栈空了");
        return (T) array[--top];
    }

    public static void main(String[] args) throws Exception {
        //数组版本  也可以用链表来做
        Stack<String> stringStack = new Stack<String>(3);
        stringStack.push("1");
        stringStack.push("2");
        stringStack.push("3");
//        stringStack.push("4");
        System.out.println(stringStack.pop());
        System.out.println(stringStack.pop());
        System.out.println(stringStack.pop());
//        System.out.println(stringStack.pop());
    }
}
