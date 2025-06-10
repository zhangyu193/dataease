package io.dataease.system.dao.auto.mapper;

import io.dataease.system.dao.auto.entity.CoreSysSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface CoreSysSettingRepository extends JpaRepository<CoreSysSetting, Long>, JpaSpecificationExecutor<CoreSysSetting> {

    void deleteByPkey(String pkey);

    @Modifying
    @Transactional
    @Query("UPDATE CoreSysSetting dv SET dv.sort = :sort WHERE dv.pkey = :pkey")
    void updateByPkey(String pkey, Integer sort);

    @Modifying
    @Transactional
    @Query("UPDATE CoreSysSetting dv SET dv.pval = :pval WHERE dv.pkey = :pkey")
    void updatePvalByPkey(String pkey, String pval);

}
