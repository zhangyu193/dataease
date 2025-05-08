package io.dataease.visualization.dao.auto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "snapshot_core_chart_view")
public class SnapshotCoreChartView {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 1024)
    @Column(name = "title", length = 1024)
    private String title;

    @NotNull
    @Column(name = "scene_id", nullable = false)
    private Long sceneId;

    @Column(name = "table_id")
    private Long tableId;

    @Size(max = 50)
    @Column(name = "type", length = 50)
    private String type;

    @Size(max = 50)
    @Column(name = "render", length = 50)
    private String render;

    @Column(name = "result_count")
    private Integer resultCount;

    @Size(max = 50)
    @Column(name = "result_mode", length = 50)
    private String resultMode;

    @Lob
    @Column(name = "x_axis", length = 16777216)
    private String xAxis;

    @Lob
    @Column(name = "x_axis_ext", length = 16777216)
    private String xAxisExt;

    @Lob
    @Column(name = "y_axis", length = 16777216)
    private String yAxis;

    @Lob
    @Column(name = "y_axis_ext", length = 16777216)
    private String yAxisExt;

    @Lob
    @Column(name = "ext_stack", length = 16777216)
    private String extStack;

    @Lob
    @Column(name = "ext_bubble", length = 16777216)
    private String extBubble;

    @Lob
    @Column(name = "ext_label", length = 16777216)
    private String extLabel;

    @Lob
    @Column(name = "ext_tooltip", length = 16777216)
    private String extTooltip;

    @Lob
    @Column(name = "custom_attr", length = 16777216)
    private String customAttr;

    @Lob
    @Column(name = "custom_attr_mobile", length = 16777216)
    private String customAttrMobile;

    @Lob
    @Column(name = "custom_style", length = 16777216)
    private String customStyle;

    @Lob
    @Column(name = "custom_style_mobile", length = 16777216)
    private String customStyleMobile;

    @Lob
    @Column(name = "custom_filter", length = 16777216)
    private String customFilter;

    @Lob
    @Column(name = "drill_fields", length = 16777216)
    private String drillFields;

    @Lob
    @Column(name = "senior", length = 16777216)
    private String senior;

    @Size(max = 50)
    @Column(name = "create_by", length = 50)
    private String createBy;

    @Column(name = "create_time")
    private Long createTime;

    @Column(name = "update_time")
    private Long updateTime;

    @Lob
    @Column(name = "snapshot", length = 16777216)
    private String snapshot;

    @Size(max = 255)
    @ColumnDefault("'panel'")
    @Column(name = "style_priority")
    private String stylePriority;

    @Size(max = 255)
    @ColumnDefault("'private'")
    @Column(name = "chart_type")
    private String chartType;

    @Column(name = "is_plugin")
    private Boolean isPlugin;

    @Size(max = 255)
    @ColumnDefault("'dataset'")
    @Column(name = "data_from")
    private String dataFrom;

    @Lob
    @Column(name = "view_fields", length = 16777216)
    private String viewFields;

    @ColumnDefault("0")
    @Column(name = "refresh_view_enable")
    private Boolean refreshViewEnable;

    @Size(max = 255)
    @ColumnDefault("'minute'")
    @Column(name = "refresh_unit")
    private String refreshUnit;

    @ColumnDefault("5")
    @Column(name = "refresh_time")
    private Integer refreshTime;

    @ColumnDefault("0")
    @Column(name = "linkage_active")
    private Boolean linkageActive;

    @ColumnDefault("0")
    @Column(name = "jump_active")
    private Boolean jumpActive;

    @Column(name = "copy_from")
    private Long copyFrom;

    @Column(name = "copy_id")
    private Long copyId;

    @Column(name = "aggregate")
    private Boolean aggregate;

    @Lob
    @Column(name = "flow_map_start_name")
    private String flowMapStartName;

    @Lob
    @Column(name = "flow_map_end_name")
    private String flowMapEndName;

    @Lob
    @Column(name = "ext_color")
    private String extColor;

    @Lob
    @Column(name = "sort_priority")
    private String sortPriority;

}
