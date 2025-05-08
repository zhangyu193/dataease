package io.dataease.share.manage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.dataease.api.xpack.share.request.TicketCreator;
import io.dataease.api.xpack.share.request.TicketDelRequest;
import io.dataease.api.xpack.share.request.TicketSwitchRequest;
import io.dataease.api.xpack.share.vo.TicketVO;
import io.dataease.api.xpack.share.vo.TicketValidVO;
import io.dataease.commons.utils.CodingUtil;
import io.dataease.exception.DEException;
import io.dataease.exportCenter.dao.auto.entity.CoreExportTask;
import io.dataease.share.dao.auto.entity.CoreShareTicket;
import io.dataease.share.dao.auto.entity.XpackShare;
import io.dataease.share.dao.auto.mapper.CoreShareTicketRepository;
import io.dataease.share.dao.auto.mapper.XpackShareMapper;
import io.dataease.share.dao.ext.mapper.XpackShareExtMapper;
import io.dataease.utils.AuthUtils;
import io.dataease.utils.BeanUtils;
import io.dataease.utils.CommonBeanFactory;
import io.dataease.utils.IDUtils;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Component
public class ShareTicketManage {

    @Resource
    private CoreShareTicketRepository coreShareTicketRepository;

    @Resource
    private XpackShareMapper xpackShareMapper;

    @Resource
    private XpackShareExtMapper xpackShareExtMapper;


    public CoreShareTicket getByTicket(String ticket) {
        Specification<CoreShareTicket> spec = (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("ticket"), ticket);
        return coreShareTicketRepository.findOne(spec).orElse(null);
    }

    @Transactional
    public String saveTicket(TicketCreator creator) {
        String ticket = creator.getTicket();
        if (StringUtils.isNotBlank(ticket)) {
            CoreShareTicket ticketEntity = getByTicket(ticket);
            if (ObjectUtils.isNotEmpty(ticketEntity)) {
                if (creator.isGenerateNew()) {
                    ticketEntity.setAccessTime(null);
                    ticketEntity.setTicket(CodingUtil.shortUuid());
                    coreShareTicketRepository.deleteById(ticketEntity.getId());
                    coreShareTicketRepository.saveAndFlush(ticketEntity);
                    return ticketEntity.getTicket();
                }
                ticketEntity.setArgs(creator.getArgs());
                ticketEntity.setExp(creator.getExp());
                ticketEntity.setUuid(creator.getUuid());
                coreShareTicketRepository.deleteById(ticketEntity.getId());
                coreShareTicketRepository.saveAndFlush(ticketEntity);
                return ticketEntity.getTicket();
            }
        }
        if (StringUtils.isBlank(ticket)) {
            ticket = CodingUtil.shortUuid();
        }
        CoreShareTicket linkTicket = new CoreShareTicket();
        linkTicket.setId(IDUtils.snowID());
        linkTicket.setTicket(ticket);
        linkTicket.setArgs(creator.getArgs());
        linkTicket.setExp(creator.getExp());
        linkTicket.setUuid(creator.getUuid());
        Objects.requireNonNull(CommonBeanFactory.proxy(this.getClass())).saveDao(linkTicket);
        return ticket;
    }

    public void saveDao(CoreShareTicket ticket) {
        coreShareTicketRepository.saveAndFlush(ticket);
    }

    public void deleteTicket(TicketDelRequest request) {
        String ticket = request.getTicket();
        if (StringUtils.isBlank(ticket)) {
            DEException.throwException("ticket为必填参数");
        }
        coreShareTicketRepository.deleteByTicket(ticket);
    }

    public void switchRequire(TicketSwitchRequest request) {
        String resourceId = request.getResourceId();
        Boolean require = request.getRequire();
        QueryWrapper<XpackShare> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("resource_id", resourceId);
        queryWrapper.eq("creator", AuthUtils.getUser().getUserId());
        XpackShare xpackShare = xpackShareMapper.selectOne(queryWrapper);
        xpackShare.setTicketRequire(require);
        xpackShareMapper.updateById(xpackShare);
    }

    public IPage<TicketVO> query(Long resourceId, Page<TicketVO> page) {
        QueryWrapper<XpackShare> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("resource_id", resourceId);
        queryWrapper.eq("creator", AuthUtils.getUser().getUserId());
        XpackShare xpackShare = xpackShareMapper.selectOne(queryWrapper);
        if (ObjectUtils.isEmpty(xpackShare)) return null;
        String uuid = xpackShare.getUuid();
        if (StringUtils.isBlank(uuid)) return null;
        QueryWrapper<CoreShareTicket> ticketQueryWrapper = new QueryWrapper<>();
        ticketQueryWrapper.eq("uuid", uuid);
        IPage<CoreShareTicket> pager = xpackShareExtMapper.pager(page, ticketQueryWrapper);
        List<CoreShareTicket> records = pager.getRecords();
        IPage<TicketVO> iPage = new Page<>();
        iPage.setPages(pager.getPages());
        iPage.setTotal(pager.getTotal());
        iPage.setCurrent(pager.getCurrent());
        iPage.setSize(pager.getSize());
        List<TicketVO> vos = records.stream().map(record -> BeanUtils.copyBean(new TicketVO(), record)).toList();
        iPage.setRecords(vos);
        return iPage;
    }

    @Transactional
    public void updateByUuidChange(String originalUuid, String newUuid) {
        xpackShareExtMapper.updateTicketUuid(originalUuid, newUuid);
    }

    @Transactional
    public void deleteByShare(String uuid) {
        coreShareTicketRepository.deleteByUuid(uuid);
    }

    public TicketValidVO validateTicket(String ticket, XpackShare share) {
        TicketValidVO vo = new TicketValidVO();
        if (StringUtils.isBlank(ticket)) {
            vo.setTicketValid(!share.getTicketRequire());
            return vo;
        }
        CoreShareTicket linkTicket = getByTicket(ticket);
        if (ObjectUtils.isEmpty(linkTicket)) {
            vo.setTicketValid(false);
            return vo;
        }
        vo.setTicketValid(true);
        vo.setArgs(linkTicket.getArgs());
        Long accessTime = linkTicket.getAccessTime();
        long now = System.currentTimeMillis();
        if (ObjectUtils.isEmpty(accessTime)) {
            accessTime = now;
            vo.setTicketExp(false);
            linkTicket.setAccessTime(accessTime);
            coreShareTicketRepository.saveAndFlush(linkTicket);
            return vo;
        }
        Long exp = linkTicket.getExp();
        if (ObjectUtils.isEmpty(exp) || exp.equals(0L)) {
            vo.setTicketExp(false);
            return vo;
        }
        long expTime = exp * 60L * 1000L;
        long time = now - accessTime;
        vo.setTicketExp(time > expTime);
        return vo;
    }

    public Integer getLimit() {
        return 0;
    }

    public long ticketCount(String uuid) {
        Specification<CoreShareTicket> spec = (root, query, cb) -> {
            var predicates = cb.conjunction();
            predicates.getExpressions().add(cb.equal(root.get("uuid"), uuid));
            return predicates;
        };
        return coreShareTicketRepository.count(spec);
    }
}
