package io.dataease.qrtz.dao.auto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "QRTZ_FIRED_TRIGGERS")
public class QrtzFiredTrigger {
    @EmbeddedId
    private QrtzFiredTriggerId id;

    @Size(max = 200)
    @NotNull
    @Column(name = "TRIGGER_NAME", nullable = false, length = 200)
    private String triggerName;

    @Size(max = 200)
    @NotNull
    @Column(name = "TRIGGER_GROUP", nullable = false, length = 200)
    private String triggerGroup;

    @Size(max = 200)
    @NotNull
    @Column(name = "INSTANCE_NAME", nullable = false, length = 200)
    private String instanceName;

    @NotNull
    @Column(name = "FIRED_TIME", nullable = false)
    private Long firedTime;

    @NotNull
    @Column(name = "SCHED_TIME", nullable = false)
    private Long schedTime;

    @NotNull
    @Column(name = "PRIORITY", nullable = false)
    private Integer priority;

    @Size(max = 16)
    @NotNull
    @Column(name = "STATE", nullable = false, length = 16)
    private String state;

    @Size(max = 200)
    @Column(name = "JOB_NAME", length = 200)
    private String jobName;

    @Size(max = 200)
    @Column(name = "JOB_GROUP", length = 200)
    private String jobGroup;

    @Size(max = 1)
    @Column(name = "IS_NONCONCURRENT", length = 1)
    private String isNonconcurrent;

    @Size(max = 1)
    @Column(name = "REQUESTS_RECOVERY", length = 1)
    private String requestsRecovery;

}
