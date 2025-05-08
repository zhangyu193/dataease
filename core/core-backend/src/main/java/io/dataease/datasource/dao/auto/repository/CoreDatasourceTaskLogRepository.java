package io.dataease.datasource.dao.auto.repository;

import io.dataease.datasource.dao.auto.entity.CoreDatasourceTaskLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface CoreDatasourceTaskLogRepository extends JpaRepository<CoreDatasourceTaskLog, Long>, JpaSpecificationExecutor<CoreDatasourceTaskLog> {


    @Modifying
    @Transactional
    @Query("DELETE FROM CoreDatasourceTaskLog cdtl WHERE cdtl.startTime < :threshold")
    void deleteByStartTimeLessThan(long threshold);

}
