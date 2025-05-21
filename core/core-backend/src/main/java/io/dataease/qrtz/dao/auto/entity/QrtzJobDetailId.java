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
public class QrtzJobDetailId implements java.io.Serializable {
    private static final long serialVersionUID = 4271480369875819749L;
    @Size(max = 120)
    @NotNull
    @Column(name = "SCHED_NAME", nullable = false, length = 120)
    private String schedName;

    @Size(max = 200)
    @NotNull
    @Column(name = "JOB_NAME", nullable = false, length = 200)
    private String jobName;

    @Size(max = 200)
    @NotNull
    @Column(name = "JOB_GROUP", nullable = false, length = 200)
    private String jobGroup;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        QrtzJobDetailId entity = (QrtzJobDetailId) o;
        return Objects.equals(this.jobName, entity.jobName) &&
                Objects.equals(this.schedName, entity.schedName) &&
                Objects.equals(this.jobGroup, entity.jobGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobName, schedName, jobGroup);
    }

}
