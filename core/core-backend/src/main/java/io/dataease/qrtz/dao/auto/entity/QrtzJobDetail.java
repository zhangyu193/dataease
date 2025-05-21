package io.dataease.qrtz.dao.auto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "QRTZ_JOB_DETAILS")
public class QrtzJobDetail {
    @EmbeddedId
    private QrtzJobDetailId id;

    @Size(max = 250)
    @Column(name = "DESCRIPTION", length = 250)
    private String description;

    @Size(max = 250)
    @NotNull
    @Column(name = "JOB_CLASS_NAME", nullable = false, length = 250)
    private String jobClassName;

    @Size(max = 1)
    @NotNull
    @Column(name = "IS_DURABLE", nullable = false, length = 1)
    private String isDurable;

    @Size(max = 1)
    @NotNull
    @Column(name = "IS_NONCONCURRENT", nullable = false, length = 1)
    private String isNonconcurrent;

    @Size(max = 1)
    @NotNull
    @Column(name = "IS_UPDATE_DATA", nullable = false, length = 1)
    private String isUpdateData;

    @Size(max = 1)
    @NotNull
    @Column(name = "REQUESTS_RECOVERY", nullable = false, length = 1)
    private String requestsRecovery;

    @Column(name = "JOB_DATA")
    private byte[] jobData;

}
