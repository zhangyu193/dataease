package io.dataease.map.dao.auto.mapper;

import io.dataease.map.dao.auto.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface AreaRepository extends JpaRepository<Area, Long>, JpaSpecificationExecutor<Area> {

    @Query("SELECT p FROM Area p WHERE p.pid = :pid")
    List<Area> findByPid156(@Param("pid") String pid);


    @Modifying
    @Transactional
    @Query("UPDATE Area a SET a.id = :newId, a.name = :newName WHERE a.id = :oldId")
    int updateArea(Long oldId, Long newId, String newName);
}
