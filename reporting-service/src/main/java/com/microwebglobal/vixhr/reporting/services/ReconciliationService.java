package com.microwebglobal.vixhr.reporting.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReconciliationService {

//    private final AttendanceRecordRepository reportRepository;
//    private final EmployeeClient employeeClient;
//    private final AttendanceClient attendanceClient;
//    private final OvertimeClient overtimeClient;

//    @Transactional
//    public void reconcile(ReconciliationEvent req) {
//        // 1. Fetch source data
//        Map<Long, EmployeeDto> employees =
//                employeeClient.getEmployees(req.getEmployeeIds())
//                        .stream()
//                        .collect(Collectors.toMap(EmployeeDto::getId, e -> e));
//
//        List<AttendanceRecordDto> attendanceList =
//                attendanceClient.getAttendanceRecords(
//                        req.getEmployeeIds(), req.getStartDate(), req.getEndDate());
//
//        Map<Long, AttendanceRecordDto> attendanceById =
//                attendanceList.stream()
//                        .collect(Collectors.toMap(AttendanceRecordDto::getId, a -> a));
//
//        List<EmployeeOvertimeDto> otList =
//                overtimeClient.getOvertimeRecords(
//                        req.getEmployeeIds(), req.getStartDate(), req.getEndDate());
//
//        Map<Long, List<EmployeeOvertimeDto>> otByEmployeeDate = otList.stream()
//                .collect(Collectors.groupingBy(
//                        ot -> Objects.hash(ot.getEmployeeId(), ot.getDate())
//                ));
//
//        // 2. Load report rows to reconcile
//        List<EmployeeAttendanceReport> reports =
//                reportRepository.findAllByCompanyIdAndDateBetween(
//                        req.getCompanyId(), req.getStartDate(), req.getEndDate());
//
//        // 3. Iterate & update
//        for (EmployeeAttendanceReport rpt : reports) {
//            EmployeeDto emp = employees.get(rpt.getEmployeeId());
//            if (emp == null) {
//                // orphaned—flag it
//                rpt.setReconciliationStatus("ORPHANED_EMPLOYEE");
//                continue;
//            }
//            // update employee fields
//            rpt.setFullName(emp.getFirstName() + " " + emp.getLastName());
//            rpt.setEmployeeCode(emp.getEmployeeCode());
//            rpt.setDepartment(emp.getDepartment().getName());
//            rpt.setJobRole(emp.getJobRole().getTitle());
//            rpt.setBaseSalary(emp.getBaseSalary());
//            rpt.setReconciliationStatus("OK");
//
//            // update attendance fields
//            AttendanceRecordDto att = attendanceById.get(rpt.getAttendanceId());
//            if (att != null) {
//                rpt.setCheckInTime(att.getCheckInTime());
//                rpt.setCheckOutTime(att.getCheckOutTime());
//                rpt.setStatus(att.getStatus());
//                rpt.setNotes(att.getNotes());
//            } else {
//                rpt.setReconciliationStatus("MISSING_ATTENDANCE");
//            }
//
//            // update overtime fields (could be multiple—here we sum & pick notes)
//            List<EmployeeOvertimeDto> ots = otByEmployeeDate.get(
//                    Objects.hash(rpt.getEmployeeId(), rpt.getDate()));
//            if (ots != null && !ots.isEmpty()) {
//                double totalHours = ots.stream().mapToDouble(EmployeeOvertimeDto::getHours).sum();
//                rpt.setIsOvertime(true);
//                rpt.setOvertimeHours(totalHours);
//                // just pick the first rate/status/notes for simplicity
//                EmployeeOvertimeDto first = ots.get(0);
//                rpt.setOvertimeRate(first.getRate());
//                rpt.setOvertimeStatus(first.getStatus());
//                rpt.setOvertimeNotes(first.getNotes());
//            } else {
//                rpt.setIsOvertime(false);
//                rpt.setOvertimeHours(null);
//                rpt.setOvertimeRate(null);
//                rpt.setOvertimeStatus(null);
//                rpt.setOvertimeNotes(null);
//            }
//        }
//
//        reportRepository.saveAll(reports);
//    }
}

