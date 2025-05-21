package io.dataease.qrtz.dao.auto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "QRTZ_TRIGGERS")
public class QrtzTrigger {
    @EmbeddedId
    private QrtzTriggerId id;

    @MapsId("id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private QrtzJobDetail qrtzJobDetails;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private QrtzJobDetail qrtzJobDetails1;

    @Size(max = 250)
    @Column(name = "DESCRIPTION", length = 250)
    private String description;

    @Column(name = "NEXT_FIRE_TIME")
    private Long nextFireTime;

    @Column(name = "PREV_FIRE_TIME")
    private Long prevFireTime;

    @Column(name = "PRIORITY")
    private Integer priority;

    @Size(max = 16)
    @NotNull
    @Column(name = "TRIGGER_STATE", nullable = false, length = 16)
    private String triggerState;

    @Size(max = 8)
    @NotNull
    @Column(name = "TRIGGER_TYPE", nullable = false, length = 8)
    private String triggerType;

    @NotNull
    @Column(name = "START_TIME", nullable = false)
    private Long startTime;

    @Column(name = "END_TIME")
    private Long endTime;

    @Size(max = 200)
    @Column(name = "CALENDAR_NAME", length = 200)
    private String calendarName;

    @Column(name = "MISFIRE_INSTR")
    private Short misfireInstr;

    @Column(name = "JOB_DATA")
    private byte[] jobData;

}
