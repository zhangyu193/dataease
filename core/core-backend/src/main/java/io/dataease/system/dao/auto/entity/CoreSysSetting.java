package io.dataease.system.dao.auto.entity;

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
@Table(name = "core_sys_setting")
public class CoreSysSetting {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "pkey", nullable = false)
    private String pkey;

    @Size(max = 255)
    @NotNull
    @Column(name = "pval", nullable = false)
    private String pval;

    @Size(max = 255)
    @NotNull
    @Column(name = "type", nullable = false)
    private String type;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "sort", nullable = false)
    private Integer sort;

}
