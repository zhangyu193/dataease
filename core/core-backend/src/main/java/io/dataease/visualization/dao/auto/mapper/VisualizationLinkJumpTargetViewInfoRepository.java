package io.dataease.visualization.dao.auto.mapper;

import io.dataease.visualization.dao.auto.entity.VisualizationLinkJumpTargetViewInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface VisualizationLinkJumpTargetViewInfoRepository extends JpaRepository<VisualizationLinkJumpTargetViewInfo, Long>, JpaSpecificationExecutor<VisualizationLinkJumpTargetViewInfo> {



}
