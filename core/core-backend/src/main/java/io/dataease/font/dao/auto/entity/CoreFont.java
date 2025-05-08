package io.dataease.font.dao.auto.entity;

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
@Table(name = "core_font")
public class CoreFont {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 255)
    @Column(name = "file_name")
    private String fileName;

    @Size(max = 255)
    @Column(name = "file_trans_name")
    private String fileTransName;

    @ColumnDefault("0")
    @Column(name = "is_default")
    private Boolean isDefault;

    @NotNull
    @Column(name = "update_time", nullable = false)
    private Long updateTime;

    @ColumnDefault("0")
    @Column(name = "is_BuiltIn")
    private Boolean isBuiltin;

    @Column(name = "size")
    private Double size;

    @Size(max = 255)
    @Column(name = "size_type")
    private String sizeType;

}
