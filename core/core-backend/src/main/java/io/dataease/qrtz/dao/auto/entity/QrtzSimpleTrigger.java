package io.dataease.qrtz.dao.auto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "QRTZ_SIMPLE_TRIGGERS")
public class QrtzSimpleTrigger {
    @EmbeddedId
    private QrtzSimpleTriggerId id;

    @MapsId("id")
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private QrtzTrigger qrtzTriggers;

    @NotNull
    @Column(name = "REPEAT_COUNT", nullable = false)
    private Long repeatCount;

    @NotNull
    @Column(name = "REPEAT_INTERVAL", nullable = false)
    private Long repeatInterval;

    @NotNull
    @Column(name = "TIMES_TRIGGERED", nullable = false)
    private Long timesTriggered;

}
