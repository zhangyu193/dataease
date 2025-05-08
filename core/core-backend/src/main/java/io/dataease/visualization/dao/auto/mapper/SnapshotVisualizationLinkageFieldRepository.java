package io.dataease.visualization.dao.auto.mapper;


import io.dataease.visualization.dao.auto.entity.SnapshotVisualizationLinkageField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface SnapshotVisualizationLinkageFieldRepository extends JpaRepository<SnapshotVisualizationLinkageField, Long>, JpaSpecificationExecutor<SnapshotVisualizationLinkageField> {



}
