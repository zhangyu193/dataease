package io.dataease.qrtz.dao.auto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "QRTZ_SCHEDULER_STATE")
public class QrtzSchedulerState {
    @EmbeddedId
    private QrtzSchedulerStateId id;

    @NotNull
    @Column(name = "LAST_CHECKIN_TIME", nullable = false)
    private Long lastCheckinTime;

    @NotNull
    @Column(name = "CHECKIN_INTERVAL", nullable = false)
    private Long checkinInterval;

}
