package io.dataease.rsa.dao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "core_rsa")
public class CoreRsa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Lob
    @Column(name = "private_key", nullable = false, length = 16777216)
    private String privateKey;

    @NotNull
    @Lob
    @Column(name = "public_key", nullable = false, length = 16777216)
    private String publicKey;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private Long createTime;

    @NotNull
    @Lob
    @Column(name = "aes_key", nullable = false, length = 16777216)
    private String aesKey;

}
