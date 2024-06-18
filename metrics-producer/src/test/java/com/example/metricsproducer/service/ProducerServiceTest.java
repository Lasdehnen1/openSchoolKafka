package com.example.metricsproducer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ProducerServiceTest {

    @Mock
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ProducerService producerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendRandomTest() {
        when(restTemplate.getForEntity(anyString(), eq(String.class)))
                .thenReturn(new ResponseEntity<>("mockMetricValue", HttpStatus.OK));

        producerService.sendRandom();

        verify(kafkaTemplate, times(1)).send(anyString(), eq("mockMetricValue"));
    }

    @Test
    void collectMetricTest() {
        String metric = "jvm.memory.used";
        String expectedResponse = "mockMetricValue";

        when(restTemplate.getForEntity(anyString(), eq(String.class)))
                .thenReturn(new ResponseEntity<>(expectedResponse, HttpStatus.OK));

        String actualResponse = producerService.collectMetric(metric);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void sendTest() {
        String message = "testMessage";

        producerService.send(message);

        verify(kafkaTemplate, times(1)).send("metrics-topic", message);
    }
}
