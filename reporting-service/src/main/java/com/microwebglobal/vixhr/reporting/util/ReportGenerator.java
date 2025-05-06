package com.microwebglobal.vixhr.reporting.util;

import com.microwebglobal.vixhr.reporting.models.AttendanceReport;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReportGenerator {

    private static final String[] HEADERS = {
            "Employee ID", "Employee Name", "Employee Code", "Department", "Job Role",
            "Base Salary", "Date", "Check-In", "Check-Out", "Status",
            "Overtime", "Overtime Hours", "Overtime Rate", "Overtime Status", "Notes"
    };

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    public ByteArrayInputStream exportToExcel(List<AttendanceReport> reports) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Attendance Reports");

            // Header
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < HEADERS.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERS[col]);
                cell.setCellStyle(createHeaderStyle(workbook));
            }

            // Rows
            int rowIdx = 1;
            for (AttendanceReport report : reports) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(report.getEmployeeId());
                row.createCell(1).setCellValue(report.getFullName());
                row.createCell(2).setCellValue(report.getEmployeeCode());
                row.createCell(3).setCellValue(report.getDepartment());
                row.createCell(4).setCellValue(report.getJobRole());
                row.createCell(5).setCellValue(report.getBaseSalary() != null ? report.getBaseSalary() : 0);
                row.createCell(6).setCellValue(report.getDate() != null ? report.getDate().format(DATE_FORMAT) : "");
                row.createCell(7).setCellValue(report.getCheckInTime() != null ? report.getCheckInTime().format(TIME_FORMAT) : "");
                row.createCell(8).setCellValue(report.getCheckOutTime() != null ? report.getCheckOutTime().format(TIME_FORMAT) : "");
                row.createCell(9).setCellValue(report.getStatus());

                row.createCell(10).setCellValue(Boolean.TRUE.equals(report.getIsOvertime()) ? "Yes" : "No");
                row.createCell(11).setCellValue(report.getOvertimeHours() != null ? report.getOvertimeHours() : 0);
                row.createCell(12).setCellValue(report.getOvertimeRate() != null ? report.getOvertimeRate() : 0);
                row.createCell(13).setCellValue(report.getOvertimeStatus());
                row.createCell(14).setCellValue(report.getNotes());
            }

            // Auto size columns
            for (int col = 0; col < HEADERS.length; col++) {
                sheet.autoSizeColumn(col);
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    private CellStyle createHeaderStyle(Workbook workbook) {
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());

        CellStyle style = workbook.createCellStyle();
        style.setFont(headerFont);
        style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }
}
