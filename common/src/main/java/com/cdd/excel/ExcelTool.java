package com.cdd.excel;

import com.alibaba.fastjson.JSON;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.util.*;

/**
 * 操作表格工具
 *
 * @author yangfengshan
 * @create 2019-06-28 17:53
 **/
public class ExcelTool {
    public static void main(String[] args) throws Exception {
        List<String> pinList = Arrays.asList("jd_7b7f9769596b1", "jd_76c477e94d91f", "jd_437746864c942", "jd_56df3dd2ac7bd", "18361279776_p", "jd_4b2dfc78902f8");
        File file = new File("d://f.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        Writer bw = new BufferedWriter(fw);
        List<List<Object>> data = excel();
        Map<String, String> map = new HashMap<>(5000);
        for (int i = 0; i < data.size(); i++) {
            map.put(String.valueOf(data.get(i)), "0");
        }
        List<ForeignExtInfo> list = new ArrayList<>();
        map.forEach((k, v) -> {
            System.out.println(k);
            ForeignExtInfo info = JSON.parseObject(com.jd.security.auth.repak.org.apache.commons.codec.binary.Base64.decodeBase64(k.getBytes()), ForeignExtInfo.
                    class);
            if (info == null) return;
//            if (pinList.contains(info.getPin())) return;
            info.setTaskId(3126320014L);
            System.out.println("输出后的pin：" + JSON.toJSONString(info));
            com.jd.security.auth.repak.org.apache.commons.codec.binary.Base64.encodeBase64String(JSON.toJSONString(info).getBytes());
            System.out.println("输出后的extinfo：" + com.jd.security.auth.repak.org.apache.commons.codec.binary.Base64.encodeBase64String(JSON.toJSONString(info).getBytes()));
            list.add(info);
            try {
                bw.write(com.jd.security.auth.repak.org.apache.commons.codec.binary.Base64.encodeBase64String(JSON.toJSONString(info).getBytes()) + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        System.out.println(map.size());
        System.out.println(map);
        System.out.println(list.size());
        System.out.println(list);
        bw.close();
    }

    public static List<List<Object>> excel() throws Exception {
        //用流的方式先读取到你想要的excel的文件
        FileInputStream fis = new FileInputStream(new File("c://副本f.xls"));

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
