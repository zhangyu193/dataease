package io.dataease.visualization.dao.auto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "visualization_link_jump")
public class VisualizationLinkJump {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "source_dv_id")
    private Long sourceDvId;

    @Column(name = "source_view_id")
    private Long sourceViewId;

    @Size(max = 4000)
    @Column(name = "link_jump_info", length = 4000)
    private String linkJumpInfo;

    @Column(name = "checked")
    private Boolean checked;

    @Column(name = "copy_from")
    private Long copyFrom;

    @Column(name = "copy_id")
    private Long copyId;

}
