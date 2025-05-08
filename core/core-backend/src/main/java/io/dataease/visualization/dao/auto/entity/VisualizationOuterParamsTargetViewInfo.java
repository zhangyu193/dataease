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
@Table(name = "visualization_outer_params_target_view_info")
public class VisualizationOuterParamsTargetViewInfo {
    @Id
    @Size(max = 50)
    @Column(name = "target_id", nullable = false, length = 50)
    private String targetId;

    @Size(max = 50)
    @Column(name = "params_info_id", length = 50)
    private String paramsInfoId;

    @Size(max = 50)
    @Column(name = "target_view_id", length = 50)
    private String targetViewId;

    @Size(max = 50)
    @Column(name = "target_field_id", length = 50)
    private String targetFieldId;

    @Size(max = 255)
    @Column(name = "copy_from")
    private String copyFrom;

    @Size(max = 50)
    @Column(name = "copy_id", length = 50)
    private String copyId;

    @Size(max = 50)
    @Column(name = "target_ds_id", length = 50)
    private String targetDsId;

}
