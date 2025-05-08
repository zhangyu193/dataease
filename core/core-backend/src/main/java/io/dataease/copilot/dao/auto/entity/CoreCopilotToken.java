package io.dataease.copilot.dao.auto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "core_copilot_token")
public class CoreCopilotToken {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "type")
    private String type;

    @Lob
    @Column(name = "token")
    private String token;

    @Column(name = "update_time")
    private Long updateTime;

}
