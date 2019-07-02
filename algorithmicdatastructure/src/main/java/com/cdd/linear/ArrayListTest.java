package com.cdd.linear;

import java.util.List;

/**
 * 数组
 *
 * @author yangfengshan
 * @create 2019-06-21 9:10
 **/
public class ArrayListTest {
    public static void main(String[] args){
        //数组可以指定位置存放 只有不超过数组长度
        int[] zu=new int[2];
        zu[1]=10;
        System.out.println(zu[0]);
        List list=new java.util.ArrayList(10);
        //ArrayList 只有操作就会抛出 IndexOutOfBoundsException
        //ArrayList 插入操作时校验数组内的数据
//        list.set(2, zu);
//        System.out.println(list.get(2));
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        //移除操作后 数组后面的数值往前移动
        list.remove(2);
        //数组 插入时会移动数据  查询时直接按照下标查找
        System.out.println(list.get(2));
    }
}
