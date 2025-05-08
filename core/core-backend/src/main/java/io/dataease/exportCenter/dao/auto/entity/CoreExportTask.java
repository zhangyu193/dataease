package io.dataease.exportCenter.dao.auto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "core_export_task")
public class CoreExportTask {
    @Id
    @Size(max = 255)
    @Column(name = "id", nullable = false)
    private String id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Size(max = 2048)
    @Column(name = "file_name", length = 2048)
    private String fileName;

    @Column(name = "file_size")
    private Double fileSize;

    @Size(max = 255)
    @Column(name = "file_size_unit")
    private String fileSizeUnit;

    @Size(max = 255)
    @Column(name = "export_from")
    private String exportFrom;

    @Size(max = 255)
    @Column(name = "export_status")
    private String exportStatus;

    @Size(max = 255)
    @Column(name = "export_from_type")
    private String exportFromType;

    @Column(name = "export_time")
    private Long exportTime;

    @Size(max = 255)
    @Column(name = "export_progress")
    private String exportProgress;

    @Size(max = 512)
    @Column(name = "export_machine_name", length = 512)
    private String exportMachineName;

    @NotNull
    @Lob
    @Column(name = "params", nullable = false, length = 16777216)
    private String params;

    @Lob
    @Column(name = "msg", length = 16777216)
    private String msg;

}
