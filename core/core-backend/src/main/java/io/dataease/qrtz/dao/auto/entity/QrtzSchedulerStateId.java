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
public class QrtzSchedulerStateId implements java.io.Serializable {
    private static final long serialVersionUID = 4901584757658234888L;
    @Size(max = 120)
    @NotNull
    @Column(name = "SCHED_NAME", nullable = false, length = 120)
    private String schedName;

    @Size(max = 200)
    @NotNull
    @Column(name = "INSTANCE_NAME", nullable = false, length = 200)
    private String instanceName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        QrtzSchedulerStateId entity = (QrtzSchedulerStateId) o;
        return Objects.equals(this.instanceName, entity.instanceName) &&
                Objects.equals(this.schedName, entity.schedName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instanceName, schedName);
    }

}
