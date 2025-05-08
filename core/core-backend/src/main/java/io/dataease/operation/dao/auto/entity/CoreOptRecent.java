package io.dataease.operation.dao.auto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "core_opt_recent")
public class CoreOptRecent {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "resource_id")
    private Long resourceId;

    @Size(max = 255)
    @Column(name = "resource_name")
    private String resourceName;

    @NotNull
    @Column(name = "uid", nullable = false)
    private Long uid;

    @NotNull
    @Column(name = "resource_type", nullable = false)
    private Integer resourceType;

    @Column(name = "opt_type")
    private Integer optType;

    @NotNull
    @Column(name = "time", nullable = false)
    private Long time;

}
