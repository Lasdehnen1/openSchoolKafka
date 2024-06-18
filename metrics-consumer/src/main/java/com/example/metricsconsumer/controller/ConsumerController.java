package com.example.metricsconsumer.controller;

import com.example.metricsconsumer.model.Metrics;
import com.example.metricsconsumer.service.ConsumerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "API for getting metrics")
@RestController
@RequiredArgsConstructor
@RequestMapping("/metrics")
public class ConsumerController {
    private final ConsumerService consumerService;

    @Operation(summary = "Get metrics by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the metric"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Metric not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Metrics> getMetricsById(
            @Parameter(description = "ID of the metric to be retrieved", required = true, example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(consumerService.getMetric(id));
    }

    @Operation(summary = "Get all metrics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all metrics")
    })
    @GetMapping
    public ResponseEntity<List<Metrics>> getAll() {
        return ResponseEntity.ok(consumerService.getAllMetric());
    }
}