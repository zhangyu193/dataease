package io.dataease.visualization.dao.auto.mapper;

import io.dataease.visualization.dao.auto.entity.VisualizationLinkage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface VisualizationLinkageRepository extends JpaRepository<VisualizationLinkage, Long>, JpaSpecificationExecutor<VisualizationLinkage> {



}
