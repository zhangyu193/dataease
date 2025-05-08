package io.dataease.visualization.dao.auto.mapper;

import io.dataease.visualization.dao.auto.entity.VisualizationSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface VisualizationSubjectRepository extends JpaRepository<VisualizationSubject, String>, JpaSpecificationExecutor<VisualizationSubject> {



}
