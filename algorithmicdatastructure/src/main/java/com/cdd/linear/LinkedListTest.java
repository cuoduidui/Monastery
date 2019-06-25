package com.cdd.linear;

import java.util.LinkedList;

/**
 * 双链表
 *
 * @author yangfengshan
 * @create 2019-06-25 9:46
 **/
public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("4");
        //删除后size变小 也会校验下标
        //删除和增加 只改变指针方向 查询需要遍历
        linkedList.remove(2);
        //set 也会校验下标
//        linkedList.set(2, "3");
        System.out.println(linkedList);
    }
}
