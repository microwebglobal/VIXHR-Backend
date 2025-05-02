package com.microwebglobal.vixhr.attendance.util;

import com.microwebglobal.vixhr.attendance.client.EmployeeClient;
import com.microwebglobal.vixhr.attendance.model.AttendanceRecord;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceReportGenerator {

    private final EmployeeClient employeeClient;

    // Generate Excel report
    public void exportToExcel(List<AttendanceRecord> records, HttpServletResponse response) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Attendance Records");

        // Header
        String[] headers = {
                "ID", "Employee Name", "Date", "Check-In", "Check-Out", "Status", "Overtime", "Overtime Hours", "Notes"
        };

        // Create headers
        ReportUtils.createHeaders(sheet, headers, workbook);

        // Data
        int rowCount = 1;

        for (AttendanceRecord record : records) {
            Row row = sheet.createRow(rowCount++);
            var employee = employeeClient.getEmployeeById(record.getEmployeeId());
            String fullName = employee.getFirstName() + " " + employee.getLastName();

            int col = 0;
            ReportUtils.createStyledCell(row, col++, String.valueOf(record.getId()), workbook);
            ReportUtils.createStyledCell(row, col++, fullName, workbook);
            ReportUtils.createStyledCell(row, col++, record.getDate().toString(), workbook);
            ReportUtils.createStyledCell(row, col++, ReportUtils.formatTime(record.getCheckInTime()), workbook);
            ReportUtils.createStyledCell(row, col++, ReportUtils.formatTime(record.getCheckOutTime()), workbook);
            ReportUtils.createStyledCell(row, col++, record.getStatus(), workbook);
            ReportUtils.createStyledCell(row, col++, String.valueOf(record.isOvertime()), workbook);
            ReportUtils.createStyledCell(row, col++, String.valueOf(record.getOvertimeHours()), workbook);
            ReportUtils.createStyledCell(row, col++, record.getNotes() != null ? record.getNotes() : "", workbook);
        }

        // Output
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=attendance_records.xlsx");

        workbook.write(response.getOutputStream());
        workbook.close();
    }

    // Generate CSV file
    public void exportToCsv(List<AttendanceRecord> records, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=attendance_records.csv");

        PrintWriter writer = response.getWriter();
        writer.println("ID,Employee Name,Date,Check-In,Check-Out,Status,Overtime,Overtime Hours,Notes");

        for (AttendanceRecord record : records) {
            var employee = employeeClient.getEmployeeById(record.getEmployeeId());
            String fullName = employee.getFirstName() + " " + employee.getLastName();

            writer.printf("%d,%s,%s,%s,%s,%s,%s,%.2f,%s%n",
                    record.getId(),
                    ReportUtils.escapeCsv(fullName),
                    record.getDate(),
                    ReportUtils.formatTime(record.getCheckInTime()),
                    ReportUtils.formatTime(record.getCheckOutTime()),
                    ReportUtils.escapeCsv(record.getStatus()),
                    record.isOvertime(),
                    record.getOvertimeHours(),
                    ReportUtils.escapeCsv(record.getNotes())
            );
        }

        writer.flush();
        writer.close();
    }
}
