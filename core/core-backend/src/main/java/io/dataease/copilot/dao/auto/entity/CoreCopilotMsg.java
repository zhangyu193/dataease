package io.dataease.copilot.dao.auto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Getter
@Setter
@Entity
@Table(name = "core_copilot_msg")
public class CoreCopilotMsg {
    @Id
    @Comment("ID")
    @Column(name = "id", nullable = false)
    private Long id;

    @Comment("用户ID")
    @Column(name = "user_id")
    private Long userId;

    @Comment("数据集ID")
    @Column(name = "dataset_group_id")
    private Long datasetGroupId;

    @Size(max = 255)
    @Comment("user or api")
    @Column(name = "msg_type")
    private String msgType;

    @Size(max = 255)
    @Comment("mysql oracle ...")
    @Column(name = "engine_type")
    private String engineType;

    @Comment("create sql")
    @Lob
    @Column(name = "schema_sql", length = 16777216)
    private String schemaSql;

    @Comment("用户提问")
    @Lob
    @Column(name = "question", length = 16777216)
    private String question;

    @Comment("历史信息")
    @Lob
    @Column(name = "history", length = 16777216)
    private String history;

    @Comment("copilot 返回 sql")
    @Lob
    @Column(name = "copilot_sql", length = 16777216)
    private String copilotSql;

    @Comment("copilot 返回信息")
    @Lob
    @Column(name = "api_msg", length = 16777216)
    private String apiMsg;

    @Comment("sql 状态")
    @Column(name = "sql_ok")
    private Integer sqlOk;

    @Comment("chart 状态")
    @Column(name = "chart_ok")
    private Integer chartOk;

    @Comment("chart 内容")
    @Lob
    @Column(name = "chart", length = 16777216)
    private String chart;

    @Comment("视图数据")
    @Lob
    @Column(name = "chart_data", length = 16777216)
    private String chartData;

    @Comment("执行请求的SQL")
    @Lob
    @Column(name = "exec_sql", length = 16777216)
    private String execSql;

    @Comment("msg状态，0失败 1成功")
    @Column(name = "msg_status")
    private Integer msgStatus;

    @Comment("创建时间")
    @Lob
    @Column(name = "err_msg", length = 16777216)
    private String errMsg;

    @Comment("创建时间")
    @Column(name = "create_time")
    private Long createTime;

}
