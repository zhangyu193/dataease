package io.dataease.visualization.dao.auto.mapper;

import io.dataease.visualization.dao.auto.entity.VisualizationLinkageField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface VisualizationLinkageFieldRepository extends JpaRepository<VisualizationLinkageField, Long>, JpaSpecificationExecutor<VisualizationLinkageField> {



}
