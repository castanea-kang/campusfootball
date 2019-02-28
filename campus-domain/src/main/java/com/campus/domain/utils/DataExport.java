package com.campus.domain.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by likang on 2018/6/27.
 */
public class DataExport {
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    public static String writeExcel(List<String> attr,List<Map> dataList, int cloumnCount,HttpServletResponse response){
        OutputStream out = null;
        String finalXlsxPath = null;
        try {
            String parentdir = System.getProperty("user.dir")+"/exportExcel/";
            File filedir = new File(parentdir);
            if(!filedir.exists()){
                filedir.mkdirs();
            }
            String serverFileName = "data_"+ System.currentTimeMillis() + ".xlsx";
            finalXlsxPath = parentdir +serverFileName;
            // 读取Excel文档
            File finalXlsxFile = new File(finalXlsxPath);
            if(!finalXlsxFile.exists()){
                createExcelFile(finalXlsxPath);
            }
            // 获取总列数
            int columnNumCount = cloumnCount;
            Workbook workBook = getWorkbok(finalXlsxFile);
            // sheet 对应一个工作页
            Sheet sheet = workBook.getSheetAt(0);
            /**
             * 删除原有数据，除了属性列
             */
            int rowNumber = sheet.getLastRowNum();  // 第一行从0开始算
            System.out.println("原始数据总行数，除属性列：" + rowNumber);
            for (int i = 1; i <= rowNumber; i++) {
                Row row = sheet.getRow(i);
                sheet.removeRow(row);
            }
            // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
            /**
             * 往Excel中写新数据
             */
            //写第一行属性名
            Row row0 = sheet.createRow(0);
            for(int n=0; n<attr.size();n++){
                Cell c = row0.createCell(n);
                c.setCellValue(attr.get(n));
            }
            //从第二行开始写数据值
            for (int j = 0; j < dataList.size(); j++) {
                // 创建一行：从第二行开始，跳过属性列
                Row row = sheet.createRow(j + 1);
                // 得到要插入的每一条记录
                Map dataMap = dataList.get(j);
//                String name = dataMap.get("name").toString();
//                String address = dataMap.get("age").toString();
//                String phone = dataMap.get("address").toString();
                for (int k = 0; k <= columnNumCount; k++) {
                    // 在一行内循环
                    for(int n=0; n<attr.size();n++){
                        Cell c = row.createCell(n);
                        c.setCellValue(dataMap.get(attr.get(n)).toString());
                    }
//                    Cell first = row.createCell(0);
//                    first.setCellValue(dataMap.get("name").toString());
//
//                    Cell second = row.createCell(1);
//                    second.setCellValue(dataMap.get("age").toString());
//
//                    Cell third = row.createCell(2);
//                    third.setCellValue(dataMap.get("address").toString());
                }
            }
            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
            response.setContentType("multipart/form-data");
//            response.setContentType("application/octet-stream");
//            response.setContentType("application/ms-excel");
            String fileName = "attachment; filename=data_" + System.currentTimeMillis() + ".xlsx";
            response.setHeader("Content-Disposition", fileName);
            OutputStream outs = response.getOutputStream();
            InputStream in = new FileInputStream(new File(finalXlsxPath));
            int b;
            while((b=in.read())!=-1){
                outs.write(b);
            }
            in.close();
            outs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                    File currentFile = new File(finalXlsxPath);
                    if (currentFile.exists()) {
                        currentFile.delete();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("数据导出成功");
        return "success";
    }
    public static Workbook getWorkbok(File file) throws IOException{
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if(file.getName().endsWith(EXCEL_XLS)){  //Excel 2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){  // Excel 2007/2010
            try {
                wb = WorkbookFactory.create(in);
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
//            wb = new XSSFWorkbook(in);
        }
        return wb;
    }


    public static boolean createExcelFile(String excelPath ) {
        boolean isCreateSuccess = false;
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook();//HSSFWorkbook();//WorkbookFactory.create(inputStream);
        }catch(Exception e) {
            System.out.println("It cause Error on CREATING excel workbook: ");
            e.printStackTrace();
        }
        if(workbook != null) {
            Sheet sheet = workbook.createSheet();
            Row row0 = sheet.createRow(0);
            for(int i = 0; i < 11; i++) {
                Cell cell_1 = row0.createCell(i, Cell.CELL_TYPE_STRING);
                CellStyle style = getStyle(workbook);
                cell_1.setCellStyle(style);
                cell_1.setCellValue("HELLO" + i + "Column");
                sheet.autoSizeColumn(i);
            }
            for (int rowNum = 1; rowNum < 200; rowNum++) {
                Row row = sheet.createRow(rowNum);
                for(int i = 0; i < 11; i++) {
                    Cell cell = row.createCell(i, Cell.CELL_TYPE_STRING);
                    cell.setCellValue("cell" + String.valueOf(rowNum+1) + String.valueOf(i+1));
                 }
             }
            try {
                FileOutputStream outputStream = new FileOutputStream(excelPath);
                workbook.write(outputStream);
                outputStream.flush();
                outputStream.close();
                isCreateSuccess = true;
             } catch (Exception e) {
                System.out.println("It cause Error on WRITTING excel workbook: ");
                e.printStackTrace();
             }
        }
        File sss = new File(excelPath);
        System.out.println(sss.getAbsolutePath());
        return isCreateSuccess;
    }
    private static CellStyle getStyle(Workbook workbook){
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        // 设置单元格字体
        Font headerFont = workbook.createFont(); // 字体
        headerFont.setFontHeightInPoints((short)14);
        headerFont.setColor(HSSFColor.RED.index);
        headerFont.setFontName("宋体");
        style.setFont(headerFont);
        style.setWrapText(true);
         // 设置单元格边框及颜色
        style.setBorderBottom((short)1);
        style.setBorderLeft((short)1);
        style.setBorderRight((short)1);
        style.setBorderTop((short)1);
        style.setWrapText(true);
        return style;
    }


    public static void main(String[] args){
        List<String> attr = new ArrayList<String>();
        attr.add("name");
        attr.add("age");
        attr.add("address");
        List<Map> lm = new ArrayList<Map>();
//        Map m0 = new HashMap<String,String>();
//        m0.put("name","name");
//        m0.put("age","age");
//        m0.put("address","address");
//        lm.add(m0);
        Map m1 = new HashMap<String,String>();
        m1.put("name","张三");
        m1.put("age","20");
        m1.put("address","haha1");
        lm.add(m1);
        Map m2 = new HashMap<String,String>();
        m2.put("name","李四");
        m2.put("age","21");
        m2.put("address","haha2");
        lm.add(m2);
        Map m3 = new HashMap<String,String>();
        m3.put("name","王五");
        m3.put("age","22");
        m3.put("address","haha3");
        lm.add(m3);
//        writeExcel(attr,lm,3,"D:\\data.xlsx");
        writeExcel(attr,lm,lm.size(),null);
    }

}
