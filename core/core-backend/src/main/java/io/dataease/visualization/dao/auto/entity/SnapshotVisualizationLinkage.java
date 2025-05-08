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
@Table(name = "snapshot_visualization_linkage")
public class SnapshotVisualizationLinkage {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "dv_id")
    private Long dvId;

    @Column(name = "source_view_id")
    private Long sourceViewId;

    @Column(name = "target_view_id")
    private Long targetViewId;

    @Column(name = "update_time")
    private Long updateTime;

    @Size(max = 255)
    @Column(name = "update_people")
    private String updatePeople;

    @ColumnDefault("0")
    @Column(name = "linkage_active")
    private Boolean linkageActive;

    @Size(max = 2000)
    @Column(name = "ext1", length = 2000)
    private String ext1;

    @Size(max = 2000)
    @Column(name = "ext2", length = 2000)
    private String ext2;

    @Column(name = "copy_from")
    private Long copyFrom;

    @Column(name = "copy_id")
    private Long copyId;

}
