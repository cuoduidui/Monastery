package com.cdd.io.io.bio;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * describe:  bio FileInputStream实例
 *
 * @author yangfengshan
 * @date 2018/12/05
 */
public class FileInputStreamTest {
    public static void main(String[] args) throws Exception {
        try (FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/README.md")) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            //readLine()阻塞直到整行读完
            String name = bufferedReader.readLine();
            String describe = bufferedReader.readLine();
            String version = bufferedReader.readLine();
            String author = bufferedReader.readLine();
            System.out.println(String.format("名称：%s ,描述：%s ,版本：%s ,作者：%s", name, describe, version, author));
        }
    }

}
