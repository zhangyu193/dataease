package io.dataease.operation.manage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.dataease.commons.constants.OptConstants;
import io.dataease.dataset.dao.auto.entity.CoreDatasetGroup;
import io.dataease.operation.dao.auto.entity.CoreOptRecent;
import io.dataease.operation.dao.auto.mapper.CoreOptRecentRepository;
import io.dataease.utils.AuthUtils;
import io.dataease.utils.IDUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.sql.ast.tree.predicate.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class CoreOptRecentManage {

    @Autowired
    private CoreOptRecentRepository coreOptRecentRepository;

    public void saveOpt(Long resourceId, int resourceType, int optType) {
        saveOpt(resourceId, null, resourceType, optType);
    }

    public void saveOpt(String resourceName, int resourceType, int optType) {
        saveOpt(null, resourceName, resourceType, optType);
    }

    public void saveOpt(Long resourceId, String resourceName, int resourceType, int optType) {
        Long uid = AuthUtils.getUser().getUserId();
        QueryWrapper<CoreOptRecent> updateWrapper = new QueryWrapper<>();
        if (resourceId != null) {
            updateWrapper.eq("resource_id", resourceId);
        }
        if (StringUtils.isNotEmpty(resourceName)) {
            updateWrapper.eq("resource_name", resourceName);
        }
        updateWrapper.eq("resource_type", resourceType);
        updateWrapper.eq("uid", uid);
        CoreOptRecent updateParam = new CoreOptRecent();
        updateParam.setOptType(optType);
        updateParam.setTime(System.currentTimeMillis());

        if (coreOptRecentRepository.updateByParams(resourceId, resourceName, resourceType, uid, optType, System.currentTimeMillis()) == 0) {
            CoreOptRecent optRecent = new CoreOptRecent();
            optRecent.setId(IDUtils.snowID());
            optRecent.setResourceId(resourceId);
            optRecent.setResourceName(resourceName);
            optRecent.setResourceType(resourceType);
            optRecent.setOptType(optType);
            optRecent.setTime(System.currentTimeMillis());
            optRecent.setUid(AuthUtils.getUser().getUserId());
            coreOptRecentRepository.saveAndFlush(optRecent);
        }
    }

    public Map<String, Long> findTemplateRecentUseTime() {
        Long uid = AuthUtils.getUser().getUserId();
        Specification<CoreOptRecent> spec = (root, query, cb) -> {
            var predicates = cb.conjunction();
            predicates.getExpressions().add(cb.equal(root.get("resourceType"), OptConstants.OPT_RESOURCE_TYPE.TEMPLATE));
            predicates.getExpressions().add(cb.equal(root.get("uid"), uid));
            return predicates;
        };
        List<CoreOptRecent> result = coreOptRecentRepository.findAll(spec);
        if (CollectionUtils.isNotEmpty(result)) {
            return result.stream().collect(Collectors.toMap(CoreOptRecent::getResourceName, CoreOptRecent::getTime));
        } else {
            return new HashMap<>();
        }
    }

}
