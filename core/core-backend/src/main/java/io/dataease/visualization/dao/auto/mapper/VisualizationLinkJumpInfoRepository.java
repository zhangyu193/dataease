package io.dataease.visualization.dao.auto.mapper;

import io.dataease.visualization.dao.auto.entity.VisualizationLinkJumpInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface VisualizationLinkJumpInfoRepository extends JpaRepository<VisualizationLinkJumpInfo, Long>, JpaSpecificationExecutor<VisualizationLinkJumpInfo> {



}
