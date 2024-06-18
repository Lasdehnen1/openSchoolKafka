package com.example.metricsconsumer.repository;

import com.example.metricsconsumer.model.Metrics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetricsRepository extends JpaRepository<Metrics, Long> {
}
