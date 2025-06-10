package io.dataease.visualization.dao.auto.mapper;

import io.dataease.dao.auto.entity.DataVisualizationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface DataVisualizationInfoRepository extends JpaRepository<DataVisualizationInfo, String>, JpaSpecificationExecutor<DataVisualizationInfo> {

    @Modifying
    @Transactional
    @Query("UPDATE DataVisualizationInfo dv SET dv.mobileLayout = 0")
    void updateMobileLayout();


    @Modifying
    @Transactional
    @Query("UPDATE DataVisualizationInfo dv SET dv.version = 2")
    void updateVersion();

    @Modifying
    @Transactional
    @Query("UPDATE DataVisualizationInfo dv SET dv.checkVersion = :checkVersion")
    void updateCheckVersion(String checkVersion);

}
