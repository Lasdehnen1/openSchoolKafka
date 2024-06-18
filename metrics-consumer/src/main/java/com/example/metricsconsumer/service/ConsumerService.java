package com.example.metricsconsumer.service;

import com.example.metricsconsumer.model.Metrics;
import com.example.metricsconsumer.repository.MetricsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumerService {
    private final MetricsRepository repository;
    private final ObjectMapper objectMapper;


    public void saveMetric(String jsonString) {
        try {
            Metrics metrics = objectMapper.readValue(jsonString, Metrics.class);
            repository.save(metrics);
        } catch (JsonProcessingException e) {
            log.error("Error parsing JSON string: {}", jsonString);
            throw new RuntimeException(e.getMessage());
        }
    }

    public Metrics getMetric(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Metrics not found with id: " + id));
    }

    public List<Metrics> getAllMetric() {
        return repository.findAll();
    }

}