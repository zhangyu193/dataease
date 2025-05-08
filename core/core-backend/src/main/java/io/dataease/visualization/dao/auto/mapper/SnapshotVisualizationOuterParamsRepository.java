package io.dataease.visualization.dao.auto.mapper;


import io.dataease.visualization.dao.auto.entity.SnapshotVisualizationOuterParams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface SnapshotVisualizationOuterParamsRepository extends JpaRepository<SnapshotVisualizationOuterParams, Long>, JpaSpecificationExecutor<SnapshotVisualizationOuterParams> {



}
