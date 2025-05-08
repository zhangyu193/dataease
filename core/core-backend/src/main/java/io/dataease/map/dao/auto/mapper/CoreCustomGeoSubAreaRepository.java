package io.dataease.map.dao.auto.mapper;

import io.dataease.map.dao.auto.entity.CoreCustomGeoSubArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface CoreCustomGeoSubAreaRepository extends JpaRepository<CoreCustomGeoSubArea, Long>, JpaSpecificationExecutor<CoreCustomGeoSubArea> {
    @Modifying
    @Transactional
    @Query("DELETE FROM CoreCustomGeoSubArea c WHERE c.geoAreaId = :areaId")
    void deleteByGeoAreaId(String areaId);
}
