package com.microwebglobal.vixhr.attendance.util;

import com.microwebglobal.vixhr.attendance.client.EmployeeClient;
import com.microwebglobal.vixhr.attendance.dto.EmployeeResponse;
import com.microwebglobal.vixhr.attendance.model.EmployeeOvertime;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OvertimeReportGenerator {

    private final EmployeeClient employeeClient;

    public void exportToExcel(List<EmployeeOvertime> records, HttpServletResponse response) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Overtime Records");

        // Headers
        String[] headers = {
                "ID", "Employee Name", "Date", "Start Time", "End Time", "Hours", "Rate", "Status", "Approved By", "Approved Date", "Notes"
        };

        // Create headers
        ReportUtils.createHeaders(sheet, headers, workbook);

        int rowCount = 1;

        for (EmployeeOvertime record : records) {
            Row row = sheet.createRow(rowCount++);
            EmployeeResponse emp = employeeClient.getEmployeeById(record.getEmployeeId());
            String fullName = emp.getFirstName() + " " + emp.getLastName();

            EmployeeResponse approvedBy = employeeClient.getEmployeeById(record.getApprovedBy());
            String approvedByName = approvedBy.getFirstName() + " " + approvedBy.getLastName();

            int col = 0;
            ReportUtils.createStyledCell(row, col++, String.valueOf(record.getId()), workbook);
            ReportUtils.createStyledCell(row, col++, fullName, workbook);
            ReportUtils.createStyledCell(row, col++, record.getDate().toString(), workbook);
            ReportUtils.createStyledCell(row, col++, ReportUtils.formatTime(record.getStartTime()), workbook);
            ReportUtils.createStyledCell(row, col++, ReportUtils.formatTime(record.getEndTime()), workbook);
            ReportUtils.createStyledCell(row, col++, String.valueOf(record.getHours()), workbook);
            ReportUtils.createStyledCell(row, col++, String.valueOf(record.getRate()), workbook);
            ReportUtils.createStyledCell(row, col++, record.getStatus(), workbook);
            ReportUtils.createStyledCell(row, col++, approvedByName, workbook);
            ReportUtils.createStyledCell(row, col++, record.getApprovedDate() != null ? record.getApprovedDate().toString() : "", workbook);
            ReportUtils.createStyledCell(row, col++, record.getNotes() != null ? record.getNotes() : "", workbook);
        }

        // Output
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=overtime_records.xlsx");

        workbook.write(response.getOutputStream());
        workbook.close();
    }

    public void exportToCsv(List<EmployeeOvertime> records, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=overtime_records.csv");

        PrintWriter writer = response.getWriter();
        writer.println("ID,Employee Name,Date,Start Time,End Time,Hours,Rate,Status,Approved By,Approved Date,Notes");

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        for (EmployeeOvertime record : records) {
            EmployeeResponse emp = employeeClient.getEmployeeById(record.getEmployeeId());
            String fullName = emp.getFirstName() + " " + emp.getLastName();

            writer.printf("%d,%s,%s,%s,%s,%.2f,%.2f,%s,%s,%s,%s%n",
                    record.getId(),
                    ReportUtils.escapeCsv(fullName),
                    record.getDate(),
                    record.getStartTime().format(timeFormatter),
                    record.getEndTime().format(timeFormatter),
                    record.getHours(),
                    record.getRate(),
                    ReportUtils.escapeCsv(record.getStatus()),
                    record.getApprovedBy() != null ? record.getApprovedBy() : "",
                    record.getApprovedDate() != null ? record.getApprovedDate().toString() : "",
                    ReportUtils.escapeCsv(record.getNotes())
            );
        }

        writer.flush();
        writer.close();
    }
}
