package io.dataease.menu.dao.auto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "core_menu")
public class CoreMenu {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "pid", nullable = false)
    private Long pid;

    @Column(name = "type")
    private Integer type;

    @Size(max = 45)
    @Column(name = "name", length = 45)
    private String name;

    @Size(max = 45)
    @Column(name = "component", length = 45)
    private String component;

    @Column(name = "menu_sort")
    private Integer menuSort;

    @Size(max = 45)
    @Column(name = "icon", length = 45)
    private String icon;

    @Size(max = 45)
    @Column(name = "path", length = 45)
    private String path;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "hidden", nullable = false)
    private Boolean hidden = false;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "in_layout", nullable = false)
    private Boolean inLayout = false;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "auth", nullable = false)
    private Boolean auth = false;

}
