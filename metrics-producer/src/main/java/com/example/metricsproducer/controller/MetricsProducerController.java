package com.example.metricsproducer.controller;

import com.example.metricsproducer.service.ProducerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "API for sending metrics")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/metrics")
public class MetricsProducerController {
    private final ProducerService producerService;

    @Operation(summary = "Send random metrics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Metrics been sent")
    })
    @GetMapping
    @PostMapping
    public ResponseEntity<String> sendMetrics() {
        producerService.sendRandom();
        return ResponseEntity.ok("Metrics sent");
    }
}
