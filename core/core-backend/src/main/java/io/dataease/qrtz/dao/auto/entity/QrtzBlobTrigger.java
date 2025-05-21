package io.dataease.qrtz.dao.auto.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "QRTZ_BLOB_TRIGGERS")
public class QrtzBlobTrigger {
    @EmbeddedId
    private QrtzBlobTriggerId id;

    @MapsId("id")
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private QrtzTrigger qrtzTriggers;

    @Column(name = "BLOB_DATA")
    private byte[] blobData;

}
