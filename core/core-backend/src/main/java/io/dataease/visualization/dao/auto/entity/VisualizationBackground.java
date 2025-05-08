package io.dataease.visualization.dao.auto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "visualization_background")
public class VisualizationBackground {
    @Id
    @Size(max = 64)
    @Column(name = "id", nullable = false, length = 64)
    private String id;

    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @Size(max = 255)
    @NotNull
    @Column(name = "classification", nullable = false)
    private String classification;

    @Lob
    @Column(name = "content")
    private String content;

    @Size(max = 255)
    @Column(name = "remark")
    private String remark;

    @Column(name = "sort")
    private Integer sort;

    @Column(name = "upload_time")
    private Long uploadTime;

    @Size(max = 255)
    @Column(name = "base_url")
    private String baseUrl;

    @Size(max = 255)
    @Column(name = "url")
    private String url;

}
