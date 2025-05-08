package io.dataease.visualization.dao.auto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "visualization_linkage_field")
public class VisualizationLinkageField {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "linkage_id")
    private Long linkageId;

    @Column(name = "source_field")
    private Long sourceField;

    @Column(name = "target_field")
    private Long targetField;

    @Column(name = "update_time")
    private Long updateTime;

    @Column(name = "copy_from")
    private Long copyFrom;

    @Column(name = "copy_id")
    private Long copyId;

}
