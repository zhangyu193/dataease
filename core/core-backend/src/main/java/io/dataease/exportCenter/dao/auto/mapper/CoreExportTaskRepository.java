package io.dataease.exportCenter.dao.auto.mapper;

import io.dataease.datasource.dao.auto.entity.CoreDeEngine;
import io.dataease.exportCenter.dao.auto.entity.CoreExportTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface CoreExportTaskRepository extends JpaRepository<CoreExportTask, String>, JpaSpecificationExecutor<CoreExportTask> {

    @Query("SELECT COUNT(et) FROM CoreExportTask et WHERE et.userId = :userId AND et.exportStatus = :exportStatus")
    long countByUserIdAndExportStatus(Long userId, String exportStatus);

    @Query("SELECT COUNT(et) FROM CoreExportTask et WHERE et.userId = :userId")
    long countByUserId(Long userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM CoreExportTask cdtl WHERE cdtl.exportTime < :threshold")
    void deleteByExportTimeLessThan(long threshold);
}
