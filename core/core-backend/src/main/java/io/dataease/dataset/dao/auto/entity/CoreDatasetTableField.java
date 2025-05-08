package io.dataease.dataset.dao.auto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "core_dataset_table_field")
public class CoreDatasetTableField {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "datasource_id")
    private Long datasourceId;

    @Column(name = "dataset_table_id")
    private Long datasetTableId;

    @Column(name = "dataset_group_id")
    private Long datasetGroupId;

    @Column(name = "chart_id")
    private Long chartId;

    @NotNull
    @Lob
    @Column(name = "origin_name", nullable = false, length = 16777216)
    private String originName;

    @Lob
    @Column(name = "name", length = 16777216)
    private String name;

    @Lob
    @Column(name = "description", length = 16777216)
    private String description;

    @Size(max = 255)
    @Column(name = "dataease_name")
    private String dataeaseName;

    @Size(max = 255)
    @Column(name = "field_short_name")
    private String fieldShortName;

    @Lob
    @Column(name = "group_list", length = 16777216)
    private String groupList;

    @Lob
    @Column(name = "other_group", length = 16777216)
    private String otherGroup;

    @Size(max = 50)
    @Column(name = "group_type", length = 50)
    private String groupType;

    @Size(max = 255)
    @NotNull
    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "size")
    private Integer size;

    @NotNull
    @Column(name = "de_type", nullable = false)
    private Integer deType;

    @NotNull
    @Column(name = "de_extract_type", nullable = false)
    private Integer deExtractType;

    @Column(name = "ext_field")
    private Integer extField;

    @ColumnDefault("1")
    @Column(name = "checked")
    private Boolean checked;

    @Column(name = "column_index")
    private Integer columnIndex;

    @Column(name = "last_sync_time")
    private Long lastSyncTime;

    @ColumnDefault("0")
    @Column(name = "accuracy")
    private Integer accuracy;

    @Size(max = 255)
    @Column(name = "date_format")
    private String dateFormat;

    @Size(max = 255)
    @Column(name = "date_format_type")
    private String dateFormatType;

    @Lob
    @Column(name = "params", length = 16777216)
    private String params;

}
