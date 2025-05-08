package io.dataease.visualization.dao.auto.mapper;

import io.dataease.visualization.dao.auto.entity.VisualizationLinkJump;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface VisualizationLinkJumpRepository extends JpaRepository<VisualizationLinkJump, Long>, JpaSpecificationExecutor<VisualizationLinkJump> {



}
