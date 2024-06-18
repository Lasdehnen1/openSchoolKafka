package com.example.metricsconsumer.controller;

import com.example.metricsconsumer.model.Metrics;
import com.example.metricsconsumer.service.ConsumerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ConsumerControllerTest {
    @Mock
    private ConsumerService consumerService;

    @InjectMocks
    private ConsumerController consumerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getMetricsByIdTest() {
        Long id = 1L;
        Metrics metrics = new Metrics();
        when(consumerService.getMetric(id)).thenReturn(metrics);
        ResponseEntity<Metrics> response = consumerController.getMetricsById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(metrics, response.getBody());
    }

    @Test
    void getAllTest() {
        List<Metrics> metricsList = List.of(new Metrics());
        when(consumerService.getAllMetric()).thenReturn(metricsList);
        ResponseEntity<List<Metrics>> response = consumerController.getAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(metricsList, response.getBody());
    }
}