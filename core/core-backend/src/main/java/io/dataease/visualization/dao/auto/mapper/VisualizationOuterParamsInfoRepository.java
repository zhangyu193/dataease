package io.dataease.visualization.dao.auto.mapper;

import io.dataease.visualization.dao.auto.entity.VisualizationOuterParamsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface VisualizationOuterParamsInfoRepository extends JpaRepository<VisualizationOuterParamsInfo, Long>, JpaSpecificationExecutor<VisualizationOuterParamsInfo> {



}
