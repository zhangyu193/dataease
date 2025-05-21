package io.dataease.dao.auto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "data_visualization_info")
public class DataVisualizationInfo {
    @Id
    @Size(max = 50)
    @Column(name = "id", nullable = false, length = 50)
    private String id;

    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @Size(max = 50)
    @Column(name = "pid", length = 50)
    private String pid;

    @Size(max = 50)
    @Column(name = "org_id", length = 50)
    private String orgId;

    @Column(name = "level")
    private Integer level;

    @Size(max = 255)
    @Column(name = "node_type")
    private String nodeType;

    @Size(max = 50)
    @Column(name = "type", length = 50)
    private String type;

    @Lob
    @Column(name = "canvas_style_data", length = 16777216)
    private String canvasStyleData;

    @Lob
    @Column(name = "component_data", length = 16777216)
    private String componentData;

    @ColumnDefault("0")
    @Column(name = "mobile_layout")
    private Byte mobileLayout;

    @ColumnDefault("1")
    @Column(name = "status")
    private Integer status;

    @ColumnDefault("0")
    @Column(name = "self_watermark_status")
    private Integer selfWatermarkStatus;

    @ColumnDefault("0")
    @Column(name = "sort")
    private Integer sort;

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

    @Size(max = 255)
    @Column(name = "remark")
    private String remark;

    @Size(max = 255)
    @Column(name = "source")
    private String source;

    @ColumnDefault("0")
    @Column(name = "delete_flag")
    private Boolean deleteFlag;

    @Column(name = "delete_time")
    private Long deleteTime;

    @Size(max = 255)
    @Column(name = "delete_by")
    private String deleteBy;

    @ColumnDefault("3")
    @Column(name = "version")
    private Integer version;

    @Size(max = 50)
    @ColumnDefault("'0'")
    @Column(name = "content_id", length = 50)
    private String contentId;

    @Size(max = 50)
    @ColumnDefault("'1'")
    @Column(name = "check_version", length = 50)
    private String checkVersion;

}
