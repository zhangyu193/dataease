package io.dataease.visualization.dao.auto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "visualization_outer_params_info")
public class VisualizationOuterParamsInfo {
    @Id
    @Size(max = 50)
    @Column(name = "params_info_id", nullable = false, length = 50)
    private String paramsInfoId;

    @Size(max = 50)
    @Column(name = "params_id", length = 50)
    private String paramsId;

    @Size(max = 255)
    @Column(name = "param_name")
    private String paramName;

    @Column(name = "checked")
    private Boolean checked;

    @Size(max = 255)
    @Column(name = "copy_from")
    private String copyFrom;

    @Size(max = 50)
    @Column(name = "copy_id", length = 50)
    private String copyId;

    @ColumnDefault("0")
    @Column(name = "required")
    private Boolean required;

    @Lob
    @Column(name = "default_value", length = 16777216)
    private String defaultValue;

    @ColumnDefault("0")
    @Column(name = "enabled_default")
    private Boolean enabledDefault;

}
