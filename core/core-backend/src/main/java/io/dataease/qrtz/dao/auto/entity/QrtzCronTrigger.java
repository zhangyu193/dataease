package io.dataease.qrtz.dao.auto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "QRTZ_CRON_TRIGGERS")
public class QrtzCronTrigger {
    @EmbeddedId
    private QrtzCronTriggerId id;

    @MapsId("id")
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private QrtzTrigger qrtzTriggers;

    @Size(max = 200)
    @NotNull
    @Column(name = "CRON_EXPRESSION", nullable = false, length = 200)
    private String cronExpression;

    @Size(max = 80)
    @Column(name = "TIME_ZONE_ID", length = 80)
    private String timeZoneId;

}
