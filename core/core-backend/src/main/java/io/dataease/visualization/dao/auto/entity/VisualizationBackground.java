package io.dataease.visualization.dao.auto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Getter
@Setter
@Comment("边框背景表")
@Entity
@Table(name = "visualization_background")
public class VisualizationBackground {
    @Id
    @Size(max = 64)
    @Comment("主键")
    @Column(name = "id", nullable = false, length = 64)
    private String id;

    @Size(max = 255)
    @Comment("名称")
    @Column(name = "name")
    private String name;

    @Size(max = 255)
    @NotNull
    @Comment("分类名")
    @Column(name = "classification", nullable = false)
    private String classification;

    @Lob
    @Column(name = "content")
    private String content;

    @Size(max = 255)
    @Comment("备注")
    @Column(name = "remark")
    private String remark;

    @Comment("排序")
    @Column(name = "sort")
    private Integer sort;

    @Comment("上传时间")
    @Column(name = "upload_time")
    private Long uploadTime;

    @Size(max = 255)
    @Comment("所在目录地址")
    @Column(name = "base_url")
    private String baseUrl;

    @Size(max = 255)
    @Comment("图片url")
    @Column(name = "url")
    private String url;


}
