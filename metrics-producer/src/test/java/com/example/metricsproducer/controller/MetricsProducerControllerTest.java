package com.example.metricsproducer.controller;

import com.example.metricsproducer.service.ProducerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MetricsProducerControllerTest {

    @Mock
    private ProducerService producerService;

    @InjectMocks
    private MetricsProducerController metricsProducerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendMetricsTest() {
        ResponseEntity<String> response =metricsProducerController.sendMetrics();

        // verifying producer service is called
        verify(producerService, times(1)).sendRandom();

        assertEquals("Metrics sent", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
