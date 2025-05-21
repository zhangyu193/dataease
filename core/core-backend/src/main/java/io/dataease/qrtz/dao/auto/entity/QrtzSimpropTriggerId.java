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
public class QrtzSimpropTriggerId implements java.io.Serializable {
    private static final long serialVersionUID = -7305434751570641741L;
    @Size(max = 120)
    @NotNull
    @Column(name = "SCHED_NAME", nullable = false, length = 120)
    private String schedName;

    @Size(max = 200)
    @NotNull
    @Column(name = "TRIGGER_NAME", nullable = false, length = 200)
    private String triggerName;

    @Size(max = 200)
    @NotNull
    @Column(name = "TRIGGER_GROUP", nullable = false, length = 200)
    private String triggerGroup;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        QrtzSimpropTriggerId entity = (QrtzSimpropTriggerId) o;
        return Objects.equals(this.triggerGroup, entity.triggerGroup) &&
                Objects.equals(this.triggerName, entity.triggerName) &&
                Objects.equals(this.schedName, entity.schedName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(triggerGroup, triggerName, schedName);
    }

}
