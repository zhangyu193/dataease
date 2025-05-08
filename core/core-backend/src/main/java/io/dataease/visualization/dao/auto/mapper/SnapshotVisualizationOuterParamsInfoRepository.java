package io.dataease.visualization.dao.auto.mapper;


import io.dataease.visualization.dao.auto.entity.SnapshotVisualizationOuterParamsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface SnapshotVisualizationOuterParamsInfoRepository extends JpaRepository<SnapshotVisualizationOuterParamsInfo, Long>, JpaSpecificationExecutor<SnapshotVisualizationOuterParamsInfo> {



}
