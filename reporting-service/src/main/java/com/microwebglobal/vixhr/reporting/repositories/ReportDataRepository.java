package com.microwebglobal.vixhr.reporting.repositories;

import com.microwebglobal.vixhr.reporting.models.ReportDataRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReportDataRepository extends JpaRepository<ReportDataRecord, Long> {

    Page<ReportDataRecord> findAllByCompanyIdAndDateBetween(Long companyId, LocalDate startDate, LocalDate endDate, Pageable pageable);

    Page<ReportDataRecord> findAllByEmployeeIdAndDateBetween(Long employeeId, LocalDate startDate, LocalDate endDate, Pageable pageable);

    List<ReportDataRecord> findAllByEmployeeIdAndDateBetweenAndStatusNot(
            Long employeeId, LocalDate dateAfter, LocalDate dateBefore, String status);

    List<ReportDataRecord> findAllByCompanyIdAndDateBetweenAndStatusNot(
            Long companyId, LocalDate dateAfter, LocalDate dateBefore, String status);


    Optional<ReportDataRecord> findByEmployeeIdAndDate(Long employeeId, LocalDate date);

    List<ReportDataRecord> findAllByCompanyId(Long companyId);

    void deleteAllByEmployeeId(Long employeeId);

    void deleteAllByCompanyId(Long companyId);
}

