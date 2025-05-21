package io.dataease.qrtz.dao.auto.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "QRTZ_LOCKS")
public class QrtzLock {
    @EmbeddedId
    private QrtzLockId id;

}
