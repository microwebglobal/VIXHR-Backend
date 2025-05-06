package com.microwebglobal.vixhr.employee.messaging;

import com.microwebglobal.vixhr.common.events.ReconciliationEvent;
import com.microwebglobal.vixhr.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReconciliationJobProducer {

    private final KafkaTemplate<String, ReconciliationEvent> kafkaTemplate;
    private final EmployeeRepository employeeRepository;

    @Scheduled(cron = "0 2 * * * *")
    public void triggerReconciliation() {
        List<Long> companyIds = employeeRepository.findDistinctCompanyIds();

        for (Long companyId : companyIds) {
            List<Long> employeeIds = employeeRepository.findActiveEmployeeIdsByCompanyId(companyId);

            if (!employeeIds.isEmpty()) {
                ReconciliationEvent request = ReconciliationEvent.builder()
                        .companyId(companyId)
                        .employeeIds(employeeIds)
                        .startDate(LocalDate.now().minusDays(1))
                        .endDate(LocalDate.now())
                        .source("scheduler")
                        .build();

                kafkaTemplate.send("attendance.reconcile", request);
                kafkaTemplate.send("overtime.reconcile", request);
            }
        }
    }
}

