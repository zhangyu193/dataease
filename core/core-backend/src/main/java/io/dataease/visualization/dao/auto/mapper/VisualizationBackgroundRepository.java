package io.dataease.visualization.dao.auto.mapper;

import io.dataease.visualization.dao.auto.entity.VisualizationBackground;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface VisualizationBackgroundRepository extends JpaRepository<VisualizationBackground, Long>, JpaSpecificationExecutor<VisualizationBackground> {



}
