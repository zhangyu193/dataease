package io.dataease.visualization.dao.auto.mapper;

import io.dataease.visualization.dao.auto.entity.VisualizationLinkJumpTargetViewInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface VisualizationLinkJumpTargetViewInfoRepository extends JpaRepository<VisualizationLinkJumpTargetViewInfo, Long>, JpaSpecificationExecutor<VisualizationLinkJumpTargetViewInfo> {


    @Modifying
    @Transactional
    @Query("UPDATE VisualizationLinkJumpTargetViewInfo c SET c.targetType = :targetType")
    int updateTargetType(String targetType);

}
