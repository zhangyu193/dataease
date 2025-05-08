package io.dataease.dataset.dao.auto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "core_dataset_table")
public class CoreDatasetTable {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 128)
    @Column(name = "name", length = 128)
    private String name;

    @Size(max = 128)
    @Column(name = "table_name", length = 128)
    private String tableName;

    @Column(name = "datasource_id")
    private Long datasourceId;

    @NotNull
    @Column(name = "dataset_group_id", nullable = false)
    private Long datasetGroupId;

    @Size(max = 50)
    @Column(name = "type", length = 50)
    private String type;

    @Lob
    @Column(name = "info", length = 16777216)
    private String info;

    @Lob
    @Column(name = "sql_variable_details", length = 16777216)
    private String sqlVariableDetails;

}
