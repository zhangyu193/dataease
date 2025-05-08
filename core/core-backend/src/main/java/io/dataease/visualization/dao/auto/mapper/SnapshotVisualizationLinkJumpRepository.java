package io.dataease.visualization.dao.auto.mapper;


import io.dataease.visualization.dao.auto.entity.SnapshotVisualizationLinkJump;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface SnapshotVisualizationLinkJumpRepository extends JpaRepository<SnapshotVisualizationLinkJump, Long>, JpaSpecificationExecutor<SnapshotVisualizationLinkJump> {



}
