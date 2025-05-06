package com.microwebglobal.vixhr.reporting.messaging;

import com.microwebglobal.vixhr.common.events.ReconciliationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReconciliationConsumer {

    @KafkaListener(topics = "report.reconcile", groupId = "report-service")
    public void handleReconciliation(ReconciliationEvent event) {
    }
}

