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
@Table(name = "visualization_outer_params")
public class VisualizationOuterParams {
    @Id
    @Size(max = 50)
    @Column(name = "params_id", nullable = false, length = 50)
    private String paramsId;

    @Size(max = 50)
    @Column(name = "visualization_id", length = 50)
    private String visualizationId;

    @Column(name = "checked")
    private Boolean checked;

    @Size(max = 255)
    @Column(name = "remark")
    private String remark;

    @Size(max = 50)
    @Column(name = "copy_from", length = 50)
    private String copyFrom;

    @Size(max = 50)
    @Column(name = "copy_id", length = 50)
    private String copyId;

}
