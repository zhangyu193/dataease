package io.dataease.traffic.dao.mapper;


import io.dataease.traffic.dao.entity.CoreApiTraffic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

public interface CoreApiTrafficRepository extends JpaRepository<CoreApiTraffic, Long>, JpaSpecificationExecutor<CoreApiTraffic> {

    @Query("SELECT t.alive FROM CoreApiTraffic t WHERE t.api = :api")
    Optional<Integer> getAlive(String api);

    @Modifying
    @Transactional
    @Query("UPDATE CoreApiTraffic t SET t.alive = t.alive + 1 WHERE t.api = :api")
    void upgrade(String api);

    @Modifying
    @Transactional
    @Query("INSERT INTO CoreApiTraffic (id, api, threshold, alive) VALUES (:id, :api, :threshold, 0)")
    void insert(Long id, String api, int threshold);

    @Query("SELECT COUNT(t) FROM CoreApiTraffic t WHERE t.api = :api")
    Integer apiCount(String api);

    @Modifying
    @Transactional
    @Query("UPDATE CoreApiTraffic t SET t.alive = CASE WHEN t.alive > 0 THEN t.alive - 1 ELSE t.alive END WHERE t.api = :api")
    void releaseAlive(String api);

    @Modifying
    @Transactional
    @Query("DELETE FROM CoreApiTraffic")
    void cleanTraffic();
}
