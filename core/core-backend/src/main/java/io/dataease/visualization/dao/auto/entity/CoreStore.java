package io.dataease.visualization.dao.auto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "core_store")
public class CoreStore {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "resource_id", nullable = false)
    private Long resourceId;

    @NotNull
    @Column(name = "uid", nullable = false)
    private Long uid;

    @NotNull
    @Column(name = "resource_type", nullable = false)
    private Integer resourceType;

    @NotNull
    @Column(name = "time", nullable = false)
    private Long time;

}
