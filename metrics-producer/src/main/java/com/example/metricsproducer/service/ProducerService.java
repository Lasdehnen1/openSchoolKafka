package com.example.metricsproducer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProducerService {
    private final KafkaTemplate<Object, Object> template;
    private final RestTemplate restTemplate;
    private static final String URL = "http://localhost:8082/actuator/metrics";

    private static final String[] METRICS_TO_COLLECT = {"jvm.memory.used", "jvm.memory.max", "system.cpu.usage", "process.uptime"};

    private final Random random = new Random();

    public void sendRandom() {
        String randomMetric = METRICS_TO_COLLECT[random.nextInt(METRICS_TO_COLLECT.length)];
        String metricValue = collectMetric(randomMetric);
        send(metricValue);
    }

    public void send(Object message) {
        log.info("Sending message: {}", message);
        template.send("metrics-topic", message);
        log.info("Metric sent");
    }

    public String collectMetric(String metric) {
        String metricsUrl = URL + "/" + metric;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(metricsUrl, String.class);
        return responseEntity.getBody();
    }
}
