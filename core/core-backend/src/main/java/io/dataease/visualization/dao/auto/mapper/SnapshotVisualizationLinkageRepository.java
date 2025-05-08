package io.dataease.visualization.dao.auto.mapper;


import io.dataease.visualization.dao.auto.entity.SnapshotVisualizationLinkage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface SnapshotVisualizationLinkageRepository extends JpaRepository<SnapshotVisualizationLinkage, Long>, JpaSpecificationExecutor<SnapshotVisualizationLinkage> {



}
