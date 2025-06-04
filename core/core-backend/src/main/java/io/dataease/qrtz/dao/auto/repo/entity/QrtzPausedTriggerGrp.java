package io.dataease.qrtz.dao.auto.repo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "QRTZ_PAUSED_TRIGGER_GRPS")
@IdClass(QrtzPausedTriggerGrpId.class)
public class QrtzPausedTriggerGrp {

    @Id
    @Column(name = "SCHED_NAME", nullable = false, length = 120)
    private String schedName;


    @Id
    @Column(name = "TRIGGER_GROUP", nullable = false, length = 200)
    private String triggerGroup;

}
