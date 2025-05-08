package io.dataease.visualization.dao.auto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "visualization_watermark")
public class VisualizationWatermark {
    @Id
    @Size(max = 50)
    @Column(name = "id", nullable = false, length = 50)
    private String id;

    @Size(max = 255)
    @Column(name = "version")
    private String version;

    @Lob
    @Column(name = "setting_content", length = 16777216)
    private String settingContent;

    @Size(max = 255)
    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_time")
    private Long createTime;

}
