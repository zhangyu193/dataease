package io.dataease.system.dao.auto.mapper;

import io.dataease.system.dao.auto.entity.CoreSysSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface CoreSysSettingRepository extends JpaRepository<CoreSysSetting, Long>, JpaSpecificationExecutor<CoreSysSetting> {

    void deleteByPkey(String pkey);

}
