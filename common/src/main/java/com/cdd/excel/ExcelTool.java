package com.cdd.excel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 操作表格工具
 *
 * @author yangfengshan
 * @create 2019-06-28 17:53
 **/
public class ExcelTool {
    public static void main(String[] args) throws Exception {
        excel();
    }

    public static List<List<Object>> excel() throws Exception {
        //用流的方式先读取到你想要的excel的文件
        FileInputStream fis = new FileInputStream(new File("d://c.xls"));

        //解析excel
        POIFSFileSystem pSystem = new POIFSFileSystem(fis);
        //获取整个excel
        HSSFWorkbook hb = new HSSFWorkbook(pSystem);
        System.out.println(hb.getNumCellStyles());
        //获取第一个表单sheet
        HSSFSheet sheet = hb.getSheetAt(0);
        //获取第一行
        int firstrow = sheet.getFirstRowNum();
        //获取最后一行
        int lastrow = sheet.getLastRowNum();
        List<List<Object>> retList = new ArrayList<List<Object>>();
        //循环行数依次获取列数
        for (int i = firstrow; i < lastrow + 1; i++) {
            //获取哪一行i
            Row row = sheet.getRow(i);
            if (row != null) {
                //获取这一行的第一列
                int firstcell = row.getFirstCellNum();
                //获取这一行的最后一列
                int lastcell = row.getLastCellNum();
                //创建一个集合，用处将每一行的每一列数据都存入集合中
                List<Object> list = new ArrayList<Object>();
                for (int j = firstcell; j < lastcell; j++) {
                    //获取第j列
                    Cell cell = row.getCell(j);

                    if (cell != null) {
                        if (j == 2) {
                            if (cell.toString() == null || "".equals(cell.toString())) {
                                continue;
                            }
                            list.add(cell.getDateCellValue());
                        } else {
                            list.add(cell.toString());
                        }
                    }
                }
                retList.add(list);

            }
        }

        fis.close();
        return retList;
    }
}
