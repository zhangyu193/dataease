package io.dataease.share.dao.auto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "core_share_ticket")
public class CoreShareTicket {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "uuid", nullable = false)
    private String uuid;

    @Size(max = 255)
    @NotNull
    @Column(name = "ticket", nullable = false)
    private String ticket;

    @Column(name = "exp")
    private Long exp;

    @Lob
    @Column(name = "args", length = 16777216)
    private String args;

    @Column(name = "access_time")
    private Long accessTime;

}
