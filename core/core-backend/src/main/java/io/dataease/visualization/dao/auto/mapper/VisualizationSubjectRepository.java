package io.dataease.visualization.dao.auto.mapper;

import io.dataease.visualization.dao.auto.entity.VisualizationSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface VisualizationSubjectRepository extends JpaRepository<VisualizationSubject, String>, JpaSpecificationExecutor<VisualizationSubject> {

    @Modifying
    @Transactional
    @Query("UPDATE VisualizationSubject dv SET dv.name = :name WHERE dv.id = :id")
    void updateNameById(String id, String name);

}
