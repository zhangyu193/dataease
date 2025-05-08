package io.dataease.visualization.dao.auto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "snapshot_visualization_link_jump_target_view_info")
public class SnapshotVisualizationLinkJumpTargetViewInfo {
    @Id
    @Column(name = "target_id", nullable = false)
    private Long id;

    @Column(name = "link_jump_info_id")
    private Long linkJumpInfoId;

    @Column(name = "source_field_active_id")
    private Long sourceFieldActiveId;

    @Size(max = 50)
    @Column(name = "target_view_id", length = 50)
    private String targetViewId;

    @Size(max = 50)
    @Column(name = "target_field_id", length = 50)
    private String targetFieldId;

    @Column(name = "copy_from")
    private Long copyFrom;

    @Column(name = "copy_id")
    private Long copyId;

    @Size(max = 50)
    @ColumnDefault("'view'")
    @Column(name = "target_type", length = 50)
    private String targetType;

}
