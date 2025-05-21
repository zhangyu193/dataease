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
public class QrtzCalendarId implements java.io.Serializable {
    private static final long serialVersionUID = -5269018033512618033L;
    @Size(max = 120)
    @NotNull
    @Column(name = "SCHED_NAME", nullable = false, length = 120)
    private String schedName;

    @Size(max = 200)
    @NotNull
    @Column(name = "CALENDAR_NAME", nullable = false, length = 200)
    private String calendarName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        QrtzCalendarId entity = (QrtzCalendarId) o;
        return Objects.equals(this.calendarName, entity.calendarName) &&
                Objects.equals(this.schedName, entity.schedName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calendarName, schedName);
    }

}
