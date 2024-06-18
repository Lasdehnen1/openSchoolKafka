package com.example.metricsconsumer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaMetricsListener {
    private final ConsumerService consumerService;

    @KafkaListener(id = "MetricGroup", topics = "metrics-topic")
    public void listen(String metrics) {
        log.info("Received: {}", metrics);
        consumerService.saveMetric(metrics);
        log.info("Saved: {}", metrics);
    }

    @KafkaListener(id = "dltGroup", topics = "topic.DLT")
    public void dltListen(byte[] in) {
        log.info("Received from dlt: {}", new String(in));
    }
}
