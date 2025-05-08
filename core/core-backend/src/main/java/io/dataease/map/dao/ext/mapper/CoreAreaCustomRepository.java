package io.dataease.map.dao.ext.mapper;

import io.dataease.map.dao.ext.entity.CoreAreaCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface CoreAreaCustomRepository extends JpaRepository<CoreAreaCustom, String>, JpaSpecificationExecutor<CoreAreaCustom> {

    @Query("SELECT p FROM CoreAreaCustom p WHERE p.pid IN :pids")
    List<CoreAreaCustom> findInPids(@Param("pids") List<String> pids);

    @Modifying
    @Transactional
    @Query("DELETE FROM CoreAreaCustom u WHERE u.id IN :ids")
    void deleteBatchIds(List<String> ids);
}
