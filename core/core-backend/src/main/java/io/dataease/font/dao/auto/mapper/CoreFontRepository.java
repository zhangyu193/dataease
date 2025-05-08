package io.dataease.font.dao.auto.mapper;

import io.dataease.font.dao.auto.entity.CoreFont;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface CoreFontRepository extends JpaRepository<CoreFont, Long>, JpaSpecificationExecutor<CoreFont> {
    List<CoreFont> findByName(String name);
    List<CoreFont> findByFileTransName(String fileTransName);
    List<CoreFont> findByisDefault(Boolean isDefault);

    @Transactional
    @Modifying
    @Query("UPDATE CoreFont f SET f.isDefault = :isDefault WHERE f.id = :id")
    int updateIsDefaultById(@Param("id") Long id, @Param("isDefault") Boolean isDefault);
}
