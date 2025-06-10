package io.dataease.map.dao.auto.mapper;

import io.dataease.map.dao.auto.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface AreaRepository extends JpaRepository<Area, String>, JpaSpecificationExecutor<Area> {

    @Query("SELECT p FROM Area p WHERE p.pid = :pid")
    List<Area> findByPid156(@Param("pid") String pid);


    @Modifying
    @Transactional
    @Query("UPDATE Area a SET a.id = :newId, a.name = :newName WHERE a.id = :oldId")
    int updateArea(String oldId, String newId, String newName);

    @Modifying
    @Transactional
    @Query("DELETE FROM Area a WHERE a.pid = :areaId OR a.id = :areaId")
    void deleteByPidOrId(String areaId);

    @Modifying
    @Transactional
    @Query("UPDATE Area a SET  a.name = :newName WHERE a.id = :id")
    int updatateNameById(String id, String newName);

    @Modifying
    @Transactional
    @Query("UPDATE Area a SET  a.pid = :newPid WHERE a.pid = :oldPid")
    int updatatePid(String newPid,String oldPid);
}
