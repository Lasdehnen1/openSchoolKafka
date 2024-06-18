package com.example.metricsconsumer.service;

import com.example.metricsconsumer.model.Metrics;
import com.example.metricsconsumer.repository.MetricsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class ConsumerServiceTest {

    @Mock
    private MetricsRepository repository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private ConsumerService consumerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveMetricsTest() throws JsonProcessingException {
        String jsonString = "{\"name\":\"jvm.memory.used\"}";
        Metrics metrics = new Metrics();

        when(objectMapper.readValue(jsonString, Metrics.class)).thenReturn(metrics);

        consumerService.saveMetric(jsonString);

        verify(repository, times(1)).save(metrics);
    }

    @Test
    void getMetricTest() {
        Long id = 1L;
        Metrics metrics = new Metrics();
        when(repository.findById(id)).thenReturn(Optional.of(metrics));

        Metrics result = consumerService.getMetric(id);

        assertEquals(metrics, result);
    }

    @Test
    void getMetricsNotFoundTest() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            consumerService.getMetric(id);
        });

        String expected = "Metrics not found with id: 1";

        assertEquals(expected, exception.getMessage());

    }

    @Test
    void getAllMetricTest() {
        List<Metrics> metricsList = List.of(new Metrics());
        when(repository.findAll()).thenReturn(metricsList);

        List<Metrics> result = consumerService.getAllMetric();

        assertEquals(metricsList, result);
    }
}