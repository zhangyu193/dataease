package io.dataease.qrtz.dao.auto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "QRTZ_CALENDARS")
public class QrtzCalendar {
    @EmbeddedId
    private QrtzCalendarId id;

    @NotNull
    @Column(name = "CALENDAR", nullable = false)
    private byte[] calendar;

}
