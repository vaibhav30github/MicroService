package com.vaibhav.view;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.vaibhav.entity.Student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by aboullaite on 2017-02-23.
 */
public class ExcelView extends AbstractXlsView{

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        // change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\"my-xls-file.xls\"");

        @SuppressWarnings("unchecked")
        List<Student> students = (List<Student>) model.get("students");

        // create excel xls sheet
        Sheet sheet = workbook.createSheet("User Detail");
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);


        // create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("Name");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("Mobile");
        header.getCell(2).setCellStyle(style);
        

        int rowCount = 1;

        for(Student student : students){
            Row userRow =  sheet.createRow(rowCount++);
            userRow.createCell(0).setCellValue(student.getId());
            userRow.createCell(1).setCellValue(student.getName());
            userRow.createCell(2).setCellValue(student.getMobile());
     

            }

    }

}
