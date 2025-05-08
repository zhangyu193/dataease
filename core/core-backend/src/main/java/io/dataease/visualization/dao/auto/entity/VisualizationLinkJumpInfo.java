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
@Table(name = "visualization_link_jump_info")
public class VisualizationLinkJumpInfo {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "link_jump_id")
    private Long linkJumpId;

    @Size(max = 255)
    @Column(name = "link_type")
    private String linkType;

    @Size(max = 255)
    @Column(name = "jump_type")
    private String jumpType;

    @Column(name = "target_dv_id")
    private Long targetDvId;

    @Column(name = "source_field_id")
    private Long sourceFieldId;

    @Size(max = 4000)
    @Column(name = "content", length = 4000)
    private String content;

    @Column(name = "checked")
    private Boolean checked;

    @Column(name = "attach_params")
    private Boolean attachParams;

    @Column(name = "copy_from")
    private Long copyFrom;

    @Column(name = "copy_id")
    private Long copyId;

    @Size(max = 255)
    @ColumnDefault("'middle'")
    @Column(name = "window_size")
    private String windowSize;

}
