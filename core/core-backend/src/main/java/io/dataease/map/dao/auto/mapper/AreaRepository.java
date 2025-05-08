package io.dataease.map.dao.auto.mapper;

import io.dataease.map.dao.auto.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AreaRepository extends JpaRepository<Area, Long>, JpaSpecificationExecutor<Area> {

    @Query("SELECT p FROM Area p WHERE p.pid = :pid")
    List<Area> findByPid156(@Param("pid") String pid);
}
