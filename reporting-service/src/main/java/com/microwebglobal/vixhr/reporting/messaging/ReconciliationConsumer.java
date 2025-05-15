package com.microwebglobal.vixhr.reporting.messaging;

import com.microwebglobal.vixhr.common.events.ReconciliationEvent;
import com.microwebglobal.vixhr.reporting.models.ReportDataRecord;
import com.microwebglobal.vixhr.reporting.repositories.ReportDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReconciliationConsumer {

    private final ReportDataRepository reportDataRepository;

    @KafkaListener(topics = "report.reconcile", groupId = "report-service")
    public void handleReconciliation(ReconciliationEvent event) {
        List<Long> receivedEmployeeIds = event.getEmployeeIds();
        Long companyId = event.getCompanyId();

        List<ReportDataRecord> existingRecords = reportDataRepository
                .findAllByCompanyId(companyId);

        for (var record : existingRecords) {
            if (!receivedEmployeeIds.contains(record.getEmployeeId())) {
                record.setRecordStatus("DELETED_EMPLOYEE");
            }
        }

        reportDataRepository.saveAll(existingRecords);
    }
}

