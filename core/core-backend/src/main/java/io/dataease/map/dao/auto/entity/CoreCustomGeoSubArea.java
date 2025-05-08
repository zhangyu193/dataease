package io.dataease.map.dao.auto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "core_custom_geo_sub_area")
public class CoreCustomGeoSubArea {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Size(max = 1024)
    @Column(name = "scope", length = 1024)
    private String scope;

    @Size(max = 50)
    @NotNull
    @Column(name = "geo_area_id", nullable = false, length = 50)
    private String geoAreaId;

}
