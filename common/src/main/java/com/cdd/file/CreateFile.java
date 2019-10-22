package com.cdd.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

/**
 * @author yangfengshan
 * @create 2019-03-27 10:06
 **/
public class CreateFile {
    public static void createFile(String fileName, String start, String end) throws Exception {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        Writer bw = new BufferedWriter(fw);
        for (int i = 0; i < 200; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(start);
            if (i < 10) {
                stringBuilder.append("00");
            } else if (i < 100) {
                stringBuilder.append("0");
            }
            stringBuilder.append(i);
            stringBuilder.append(end);
            bw.write(stringBuilder.toString());
        }
        bw.close();
    }
}
