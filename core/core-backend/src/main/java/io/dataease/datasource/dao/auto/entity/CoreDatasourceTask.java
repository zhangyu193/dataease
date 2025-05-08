package io.dataease.datasource.dao.auto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "core_datasource_task")
public class CoreDatasourceTask {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "ds_id", nullable = false)
    private Long dsId;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 50)
    @NotNull
    @Column(name = "update_type", nullable = false, length = 50)
    private String updateType;

    @Column(name = "start_time")
    private Long startTime;

    @Size(max = 50)
    @NotNull
    @Column(name = "sync_rate", nullable = false, length = 50)
    private String syncRate;

    @Size(max = 255)
    @Column(name = "cron")
    private String cron;

    @Column(name = "simple_cron_value")
    private Long simpleCronValue;

    @Size(max = 50)
    @Column(name = "simple_cron_type", length = 50)
    private String simpleCronType;

    @Size(max = 50)
    @Column(name = "end_limit", length = 50)
    private String endLimit;

    @Column(name = "end_time")
    private Long endTime;

    @Column(name = "create_time")
    private Long createTime;

    @Column(name = "last_exec_time")
    private Long lastExecTime;

    @Size(max = 50)
    @Column(name = "last_exec_status", length = 50)
    private String lastExecStatus;

    @Lob
    @Column(name = "extra_data", length = 16777216)
    private String extraData;

    @Size(max = 50)
    @Column(name = "task_status", length = 50)
    private String taskStatus;

}
