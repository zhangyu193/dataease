package io.dataease.copilot.dao.auto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "core_copilot_msg")
public class CoreCopilotMsg {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "dataset_group_id")
    private Long datasetGroupId;

    @Size(max = 255)
    @Column(name = "msg_type")
    private String msgType;

    @Size(max = 255)
    @Column(name = "engine_type")
    private String engineType;

    @Lob
    @Column(name = "schema_sql", length = 16777216)
    private String schemaSql;

    @Lob
    @Column(name = "question", length = 16777216)
    private String question;

    @Lob
    @Column(name = "history", length = 16777216)
    private String history;

    @Lob
    @Column(name = "copilot_sql", length = 16777216)
    private String copilotSql;

    @Lob
    @Column(name = "api_msg", length = 16777216)
    private String apiMsg;

    @Column(name = "sql_ok")
    private Integer sqlOk;

    @Column(name = "chart_ok")
    private Integer chartOk;

    @Lob
    @Column(name = "chart", length = 16777216)
    private String chart;

    @Lob
    @Column(name = "chart_data", length = 16777216)
    private String chartData;

    @Lob
    @Column(name = "exec_sql", length = 16777216)
    private String execSql;

    @Column(name = "msg_status")
    private Integer msgStatus;

    @Lob
    @Column(name = "err_msg", length = 16777216)
    private String errMsg;

    @Column(name = "create_time")
    private Long createTime;

}
