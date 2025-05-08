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
@Table(name = "core_dataset_table_sql_log")
public class CoreDatasetTableSqlLog {
    @Id
    @Size(max = 50)
    @ColumnDefault("''")
    @Column(name = "id", nullable = false, length = 50)
    private String id;

    @Size(max = 50)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "table_id", nullable = false, length = 50)
    private String tableId;

    @Column(name = "start_time")
    private Long startTime;

    @Column(name = "end_time")
    private Long endTime;

    @Column(name = "spend")
    private Long spend;

    @NotNull
    @Lob
    @Column(name = "`sql`", nullable = false, length = 16777216)
    private String sql;

    @Size(max = 45)
    @Column(name = "status", length = 45)
    private String status;

}
