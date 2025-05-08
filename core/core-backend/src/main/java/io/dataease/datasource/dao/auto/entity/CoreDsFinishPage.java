package io.dataease.datasource.dao.auto.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "core_ds_finish_page")
public class CoreDsFinishPage {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

}
