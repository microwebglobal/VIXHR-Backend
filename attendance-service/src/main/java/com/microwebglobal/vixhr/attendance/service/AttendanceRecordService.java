package com.microwebglobal.vixhr.attendance.service;

import com.microwebglobal.vixhr.attendance.dto.AttendanceRequest;
import com.microwebglobal.vixhr.attendance.model.AttendanceRecord;
import com.microwebglobal.vixhr.attendance.repository.AttendanceRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceRecordService {

    private final AttendanceRecordRepository attendanceRecordRepository;

    public List<AttendanceRecord> getAttendanceByEmployeeId(Long employeeId) {
        return attendanceRecordRepository.findAllByEmployeeId(employeeId);
    }

    public AttendanceRecord getAttendanceRecordById(Long id) {
        return attendanceRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance record not found for ID: " + id));
    }

    public AttendanceRecord createAttendanceRecord(AttendanceRecord record) {
        return attendanceRecordRepository.save(record);
    }

    public AttendanceRecord updateAttendanceRecord(Long id, AttendanceRecord record) {
        attendanceRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance record not found for ID: " + id));

        record.setId(id);
        return attendanceRecordRepository.save(record);
    }

    public void deleteAttendanceRecord(Long id) {
        var record = attendanceRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance record not found for ID: " + id));

        attendanceRecordRepository.delete(record);
    }

    public void clockIn(AttendanceRequest request) {
        var attendanceRecord = AttendanceRecord.builder()
                .employeeId(request.getEmployeeId())
                .checkInAddress(request.getAddress())
                .checkInDeviceId(request.getDeviceId())
                .checkInIp(request.getIpAddress())
                .checkInTime(LocalTime.now())
                .date(LocalDate.now())
                .status("Present")
                .build();

        attendanceRecordRepository.save(attendanceRecord);
    }

    public void clockOut(AttendanceRequest request) {
        var attendanceRecord = attendanceRecordRepository
                .findByEmployeeIdAndDate(request.getEmployeeId(), LocalDate.now())
                .orElseThrow(() -> new RuntimeException("Attendance record not found for employee ID: " + request.getEmployeeId()));

        attendanceRecord.setCheckoutAddress(request.getAddress());
        attendanceRecord.setCheckoutDeviceId(request.getDeviceId());
        attendanceRecord.setCheckoutIp(request.getIpAddress());
        attendanceRecord.setCheckOutTime(LocalTime.now());
        attendanceRecord.setStatus("Present");

        attendanceRecordRepository.save(attendanceRecord);
    }
}
