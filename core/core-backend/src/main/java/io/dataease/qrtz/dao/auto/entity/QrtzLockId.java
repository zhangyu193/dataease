package io.dataease.qrtz.dao.auto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class QrtzLockId implements java.io.Serializable {
    private static final long serialVersionUID = 8369021984150607360L;
    @Size(max = 120)
    @NotNull
    @Column(name = "SCHED_NAME", nullable = false, length = 120)
    private String schedName;

    @Size(max = 40)
    @NotNull
    @Column(name = "LOCK_NAME", nullable = false, length = 40)
    private String lockName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        QrtzLockId entity = (QrtzLockId) o;
        return Objects.equals(this.schedName, entity.schedName) &&
                Objects.equals(this.lockName, entity.lockName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schedName, lockName);
    }

}
