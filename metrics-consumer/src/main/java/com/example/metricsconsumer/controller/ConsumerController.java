package com.example.metricsconsumer.controller;

import com.example.metricsconsumer.model.Metrics;
import com.example.metricsconsumer.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/metrics")
public class ConsumerController {
    private final ConsumerService consumerService;

    @GetMapping("/{id}")
    public ResponseEntity<Metrics> getMetricsById(@PathVariable Long id) {
        return ResponseEntity.ok(consumerService.getMetric(id));
    }

    @GetMapping
    public ResponseEntity<List<Metrics>> getAll() {
        return ResponseEntity.ok(consumerService.getAllMetric());
    }
}