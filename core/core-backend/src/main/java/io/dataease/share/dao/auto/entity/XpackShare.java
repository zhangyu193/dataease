package io.dataease.share.dao.auto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "xpack_share")
public class XpackShare {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "creator", nullable = false)
    private Long creator;

    @NotNull
    @Column(name = "time", nullable = false)
    private Long time;

    @Column(name = "exp")
    private Long exp;

    @Size(max = 16)
    @NotNull
    @Column(name = "uuid", nullable = false, length = 16)
    private String uuid;

    @Size(max = 255)
    @Column(name = "pwd")
    private String pwd;

    @NotNull
    @Column(name = "resource_id", nullable = false)
    private Long resourceId;

    @NotNull
    @Column(name = "oid", nullable = false)
    private Long oid;

    @NotNull
    @Column(name = "type", nullable = false)
    private Integer type;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "auto_pwd", nullable = false)
    private Boolean autoPwd = false;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "ticket_require", nullable = false)
    private Boolean ticketRequire = false;

}
