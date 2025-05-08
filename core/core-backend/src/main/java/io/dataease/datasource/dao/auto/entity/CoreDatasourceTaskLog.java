package io.dataease.datasource.dao.auto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "core_datasource_task_log")
public class CoreDatasourceTaskLog {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "ds_id", nullable = false)
    private Long dsId;

    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "start_time")
    private Long startTime;

    @Column(name = "end_time")
    private Long endTime;

    @Size(max = 50)
    @NotNull
    @Column(name = "task_status", nullable = false, length = 50)
    private String taskStatus;

    @Size(max = 255)
    @NotNull
    @Column(name = "table_name", nullable = false)
    private String tableName;

    @Lob
    @Column(name = "info", length = 16777216)
    private String info;

    @Column(name = "create_time")
    private Long createTime;

    @Size(max = 45)
    @Column(name = "trigger_type", length = 45)
    private String triggerType;

}
