package com.example.metricsconsumer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Measurement {

    @Column(name = "statistic")
    private String statistic;

    @Column(name = "value")
    private Double value;
}