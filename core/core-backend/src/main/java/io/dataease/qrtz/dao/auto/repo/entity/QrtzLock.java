package io.dataease.qrtz.dao.auto.repo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "QRTZ_LOCKS")
@IdClass(QrtzLockId.class)
public class QrtzLock {
    @Id
    @Column(name = "SCHED_NAME", length = 120, nullable = false)
    private String schedName;

    @Id
    @Column(name = "LOCK_NAME", length = 40, nullable = false)
    private String lockName;

}
