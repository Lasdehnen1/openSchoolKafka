package com.example.metricsconsumer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "metrics")
@NoArgsConstructor
@AllArgsConstructor
public class Metrics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "base_unit")
    private String baseUnit;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "measurements", joinColumns = @JoinColumn(name = "metrics_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "statistic", column = @Column(name = "statistic")),
            @AttributeOverride(name = "value", column = @Column(name = "value"))})
    private List<Measurement> measurements;

}