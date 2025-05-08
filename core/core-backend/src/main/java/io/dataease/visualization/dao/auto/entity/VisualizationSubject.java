package io.dataease.visualization.dao.auto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "visualization_subject")
public class VisualizationSubject {
    @Id
    @Size(max = 50)
    @Column(name = "id", nullable = false, length = 50)
    private String id;

    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @Size(max = 255)
    @Column(name = "type")
    private String type;

    @Lob
    @Column(name = "details", length = 16777216)
    private String details;

    @ColumnDefault("0")
    @Column(name = "delete_flag")
    private Boolean deleteFlag;

    @Lob
    @Column(name = "cover_url", length = 16777216)
    private String coverUrl;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "create_num", nullable = false)
    private Integer createNum;

    @Column(name = "create_time")
    private Long createTime;

    @Size(max = 255)
    @Column(name = "create_by")
    private String createBy;

    @Column(name = "update_time")
    private Long updateTime;

    @Size(max = 255)
    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "delete_time")
    private Long deleteTime;

    @Column(name = "delete_by")
    private Long deleteBy;

}
