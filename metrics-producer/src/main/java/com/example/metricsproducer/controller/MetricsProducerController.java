package com.example.metricsproducer.controller;

import com.example.metricsproducer.service.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/metrics")
public class MetricsProducerController {
    private final ProducerService producerService;

    @PostMapping
    public ResponseEntity<String> sendMetrics() {
        producerService.sendRandom();
        return ResponseEntity.ok("Metrics sent");
    }
}
