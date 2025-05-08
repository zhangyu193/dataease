package io.dataease.traffic.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "core_api_traffic")
public class CoreApiTraffic {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "api", nullable = false)
    private String api;

    @NotNull
    @ColumnDefault("2")
    @Column(name = "threshold", nullable = false)
    private Integer threshold;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "alive", nullable = false)
    private Integer alive;

}
