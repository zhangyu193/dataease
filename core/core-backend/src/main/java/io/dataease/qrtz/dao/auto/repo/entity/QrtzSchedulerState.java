package io.dataease.qrtz.dao.auto.repo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "QRTZ_SCHEDULER_STATE")
@IdClass(QrtzSchedulerStateId.class)
public class QrtzSchedulerState {
    @Id
    @Column(name = "SCHED_NAME", length = 120, nullable = false)
    private String schedName;

    @Id
    @Column(name = "INSTANCE_NAME", length = 200, nullable = false)
    private String instanceName;
    @NotNull
    @Column(name = "LAST_CHECKIN_TIME", nullable = false)
    private Long lastCheckinTime;

    @NotNull
    @Column(name = "CHECKIN_INTERVAL", nullable = false)
    private Long checkinInterval;

}
