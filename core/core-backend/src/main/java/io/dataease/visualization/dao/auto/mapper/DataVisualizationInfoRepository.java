package io.dataease.visualization.dao.auto.mapper;

import io.dataease.dao.auto.entity.DataVisualizationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface DataVisualizationInfoRepository extends JpaRepository<DataVisualizationInfo, String>, JpaSpecificationExecutor<DataVisualizationInfo> {



}
