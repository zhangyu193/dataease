package io.dataease.dataset.dao.auto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "core_dataset_group")
public class CoreDatasetGroup {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 128)
    @Column(name = "name", length = 128)
    private String name;

    @Column(name = "pid")
    private Long pid;

    @ColumnDefault("0")
    @Column(name = "level")
    private Integer level;

    @Size(max = 50)
    @NotNull
    @Column(name = "node_type", nullable = false, length = 50)
    private String nodeType;

    @Size(max = 50)
    @Column(name = "type", length = 50)
    private String type;

    @ColumnDefault("0")
    @Column(name = "mode")
    private Integer mode;

    @Lob
    @Column(name = "info", length = 16777216)
    private String info;

    @Size(max = 50)
    @Column(name = "create_by", length = 50)
    private String createBy;

    @Column(name = "create_time")
    private Long createTime;

    @Size(max = 1024)
    @Column(name = "qrtz_instance", length = 1024)
    private String qrtzInstance;

    @Size(max = 45)
    @Column(name = "sync_status", length = 45)
    private String syncStatus;

    @Size(max = 50)
    @Column(name = "update_by", length = 50)
    private String updateBy;

    @ColumnDefault("0")
    @Column(name = "last_update_time")
    private Long lastUpdateTime;

    @Lob
    @Column(name = "union_sql", length = 16777216)
    private String unionSql;

    @Column(name = "is_cross")
    private Boolean isCross;

}
