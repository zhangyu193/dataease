package io.dataease.visualization.dao.auto.mapper;


import io.dataease.visualization.dao.auto.entity.SnapshotCoreChartView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface SnapshotCoreChartViewRepository extends JpaRepository<SnapshotCoreChartView, Long>, JpaSpecificationExecutor<SnapshotCoreChartView> {



}
