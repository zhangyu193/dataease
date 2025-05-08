package io.dataease.chart.manage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dataease.api.chart.vo.ChartBaseVO;
import io.dataease.api.chart.vo.ViewSelectorVO;
import io.dataease.chart.dao.auto.entity.CoreChartView;
import io.dataease.chart.dao.auto.mapper.CoreChartViewRepository;
import io.dataease.chart.dao.ext.entity.ChartBasePO;
import io.dataease.chart.dao.ext.mapper.ExtChartViewMapper;
import io.dataease.constant.CommonConstants;
import io.dataease.dataset.dao.auto.entity.CoreDatasetTableField;
import io.dataease.dataset.dao.auto.mapper.CoreDatasetTableFieldRepository;
import io.dataease.dataset.manage.DatasetTableFieldManage;
import io.dataease.dataset.manage.PermissionManage;
import io.dataease.dataset.utils.TableUtils;
import io.dataease.engine.constant.ExtFieldConstant;
import io.dataease.engine.func.FunctionConstant;
import io.dataease.engine.utils.Utils;
import io.dataease.exception.DEException;
import io.dataease.extensions.datasource.api.PluginManageApi;
import io.dataease.extensions.datasource.dto.CalParam;
import io.dataease.extensions.datasource.dto.DatasetTableFieldDTO;
import io.dataease.extensions.datasource.dto.FieldGroupDTO;
import io.dataease.extensions.datasource.model.SQLObj;
import io.dataease.extensions.view.dto.*;
import io.dataease.extensions.view.filter.FilterTreeObj;
import io.dataease.i18n.Lang;
import io.dataease.i18n.Translator;
import io.dataease.license.config.XpackInteract;
import io.dataease.utils.BeanUtils;
import io.dataease.utils.IDUtils;
import io.dataease.utils.JsonUtil;
import io.dataease.utils.LogUtil;
import io.dataease.visualization.dao.auto.entity.DataVisualizationInfo;
import io.dataease.visualization.dao.auto.entity.SnapshotCoreChartView;
import io.dataease.visualization.dao.auto.mapper.DataVisualizationInfoRepository;
import io.dataease.visualization.dao.auto.mapper.SnapshotCoreChartViewRepository;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author Junjun
 */
@Component
public class ChartViewManege {
    @Resource
    private CoreChartViewRepository coreChartViewRepository;
    @Resource
    private SnapshotCoreChartViewRepository snapshotCoreChartViewRepository;
    @Resource
    private ChartDataManage chartDataManage;
    @Resource
    private CoreDatasetTableFieldRepository coreDatasetTableFieldRepository;
    @Resource
    private PermissionManage permissionManage;

    @Resource
    private DataVisualizationInfoRepository dataVisualizationInfoRepository;

    @Resource
    private ExtChartViewMapper extChartViewMapper;

    @Resource
    private DatasetTableFieldManage datasetTableFieldManage;
    @Autowired(required = false)
    private PluginManageApi pluginManage;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Transactional
    public ChartViewDTO save(ChartViewDTO chartViewDTO) throws Exception {
        if (chartViewDTO.getTitle().length() > 100) {
            DEException.throwException(Translator.get("i18n_name_limit_100"));
        }
        Long id = chartViewDTO.getId();
        if (id == null) {
            DEException.throwException(Translator.get("i18n_no_id"));
        }
        SnapshotCoreChartView coreChartView = snapshotCoreChartViewRepository.findById(id).orElse(null);
        SnapshotCoreChartView record = transDTO2Record(chartViewDTO);
        if (ObjectUtils.isEmpty(coreChartView)) {
            snapshotCoreChartViewRepository.deleteById(record.getId());
        }
        snapshotCoreChartViewRepository.saveAndFlush(record);
        return chartViewDTO;
    }

    public void delete(Long id) {
        coreChartViewRepository.deleteById(id);
    }

    @XpackInteract(value = "chartViewManage")
    public void disuse(List<Long> chartIdList) {
    }

    //镜像操作发布
    @XpackInteract(value = "chartViewManage")
    public void publishThreshold(Long resourceId, List<Long> chartIdList) {
    }

    //镜像操作删除
    @XpackInteract(value = "chartViewManage")
    public void removeThreshold(Long resourceId, String resourceTable) {

    }

    //镜像操作恢复
    @XpackInteract(value = "chartViewManage")
    public void restoreThreshold(Long resourceId, String resourceTable) {
    }

    @Transactional
    public void deleteBySceneId(Long sceneId, List<Long> chartIds) {
        QueryWrapper<CoreChartView> wrapper = new QueryWrapper<>();
        coreChartViewRepository.deleteBySceneIdAndNotInIds(sceneId, chartIds);
    }

    public ChartViewDTO getDetails(Long id, String resourceTable) {
        CoreChartView coreChartView = null;
        if (CommonConstants.RESOURCE_TABLE.SNAPSHOT.equals(resourceTable)) {
            SnapshotCoreChartView snapshotCoreChartView = snapshotCoreChartViewRepository.findById(id).orElse(null);
            if (ObjectUtils.isEmpty(snapshotCoreChartView)) {
                return null;
            }
            coreChartView = new CoreChartView();
            BeanUtils.copyBean(coreChartView, snapshotCoreChartView);
        } else {
            coreChartView = coreChartViewRepository.findById(id).orElse(null);
            if (ObjectUtils.isEmpty(coreChartView)) {
                return null;
            }
        }
        ChartViewDTO dto = transRecord2DTO(coreChartView);
        return dto;
    }

    /**
     * sceneId 为仪表板或者数据大屏id
     */
    public List<ChartViewDTO> listBySceneId(Long sceneId, String resourceTable) {
        QueryWrapper<CoreChartView> wrapper = new QueryWrapper<>();
        wrapper.eq("scene_id", sceneId);
        List<ChartViewDTO> chartViewDTOS = transChart(extChartViewMapper.selectListCustom(sceneId, resourceTable));
        if (!CollectionUtils.isEmpty(chartViewDTOS)) {
            List<Long> tableIds = chartViewDTOS.stream()
                    .map(ChartViewDTO::getTableId)
                    .filter(tableId -> tableId != null) // 过滤掉空值
                    .distinct()
                    .toList();
            if (!CollectionUtils.isEmpty(tableIds)) {
                List<CoreDatasetTableField> coreDatasetTableFields = coreDatasetTableFieldRepository.findByDatasetGroupIdIn(tableIds);
                Map<Long, List<CoreDatasetTableField>> groupedByTableId = coreDatasetTableFields.stream()
                        .collect(Collectors.groupingBy(CoreDatasetTableField::getDatasetGroupId));
                if (chartViewDTOS.size() < 10) {
                    chartViewDTOS.forEach(dto -> {
                        if (dto.getTableId() != null) {
                            dto.setCalParams(Utils.getParams(datasetTableFieldManage.transDTO(groupedByTableId.get(dto.getTableId()))));
                        }
                    });
                } else {
                    ExecutorService executor = Executors.newFixedThreadPool(10);
                    try {
                        // 超过10个图表要处理启用多线程处理
                        CountDownLatch latch = new CountDownLatch(chartViewDTOS.size());
                        chartViewDTOS.forEach(dto -> {
                            executor.submit(() -> {
                                try {
                                    if (dto.getTableId() != null) {
                                        dto.setCalParams(Utils.getParams(datasetTableFieldManage.transDTO(groupedByTableId.get(dto.getTableId()))));
                                    }
                                } finally {
                                    latch.countDown(); // 减少计数器
                                }
                            });
                        });

                        // 等待所有线程完成
                        boolean completedInTime = latch.await(200, TimeUnit.SECONDS);
                        if (!completedInTime) {
                            throw new InterruptedException("Tasks did not complete within 200 seconds");
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        LogUtil.error(e);
                    } finally {
                        executor.shutdown(); // 确保线程池关闭
                    }
                }

            }
        }
        return chartViewDTOS;
    }

    public List<ChartViewDTO> transChart(List<CoreChartView> list) {
        if (ObjectUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(ele -> {
            ChartViewDTO dto = transRecord2DTO(ele);
            return dto;
        }).collect(Collectors.toList());
    }

    public ChartViewDTO getChart(Long id, String resourceTable) throws Exception {
        ChartViewDTO details = getDetails(id, resourceTable);
        if (details == null) {
            return null;
        }
        return chartDataManage.calcData(details);
    }

    public Map<String, List<ChartViewFieldDTO>> listByDQ(Long id, Long chartId, ChartViewDTO chartViewDTO) {
        TypeReference<List<CalParam>> typeToken = new TypeReference<>() {
        };
        TypeReference<List<FieldGroupDTO>> groupTokenType = new TypeReference<>() {
        };
        Specification<CoreDatasetTableField> spec = (root, query, cb) -> {
            var predicates = cb.conjunction();
            predicates.getExpressions().add(cb.equal(root.get("datasetGroupId"), id));
            predicates.getExpressions().add(cb.isTrue(root.get("checked")));
            predicates.getExpressions().add(cb.isNull(root.get("chartId")));
            return predicates;
        };
        List<CoreDatasetTableField> fields = coreDatasetTableFieldRepository.findAll(spec);
        List<DatasetTableFieldDTO> collect = fields.stream().map(ele -> {
            DatasetTableFieldDTO dto = new DatasetTableFieldDTO();
            BeanUtils.copyBean(dto, ele);
            dto.setParams(JsonUtil.parseList(ele.getParams(), typeToken));
            dto.setGroupList(JsonUtil.parseList(ele.getGroupList(), groupTokenType));
            return dto;
        }).collect(Collectors.toList());
        // filter column disable field
        Map<String, ColumnPermissionItem> desensitizationList = new HashMap<>();
        List<DatasetTableFieldDTO> datasetTableFieldDTOS = permissionManage.filterColumnPermissions(collect, desensitizationList, id, null);
        datasetTableFieldDTOS.forEach(ele -> ele.setDesensitized(desensitizationList.containsKey(ele.getDataeaseName())));
        datasetTableFieldDTOS.add(createCountField(id));
        List<ChartViewFieldDTO> list = transFieldDTO(datasetTableFieldDTOS);

        // 获取图表计算字段
        List<DatasetTableFieldDTO> chartFields = coreDatasetTableFieldRepository.findByChartId(chartId).stream().map(ele -> {
            DatasetTableFieldDTO dto = new DatasetTableFieldDTO();
            BeanUtils.copyBean(dto, ele);
            dto.setGroupList(JsonUtil.parseList(ele.getGroupList(), groupTokenType));
            return dto;
        }).collect(Collectors.toList());
        list.addAll(transFieldDTO(chartFields));

        // 获取list中的聚合函数，将字段的summary设置成空
        SQLObj tableObj = new SQLObj();
        tableObj.setTableAlias("");

        for (ChartViewFieldDTO ele : list) {
            if (Objects.equals(ele.getExtField(), ExtFieldConstant.EXT_CALC)) {
                List<DatasetTableFieldDTO> f = list.stream().map(e -> {
                    DatasetTableFieldDTO dto = new DatasetTableFieldDTO();
                    BeanUtils.copyBean(dto, e);
                    return dto;
                }).collect(Collectors.toList());
                String originField = Utils.calcFieldRegex(ele, tableObj, f, true, null, Utils.mergeParam(Utils.getParams(f), null), pluginManage);
                for (String func : FunctionConstant.AGG_FUNC) {
                    if (Utils.matchFunction(func, originField)) {
                        ele.setSummary("");
                        ele.setAgg(true);
                        break;
                    }
                }
            }
        }

        List<ChartViewFieldDTO> dimensionList = list.stream().filter(ele -> StringUtils.equalsIgnoreCase(ele.getGroupType(), "d")).collect(Collectors.toList());
        List<ChartViewFieldDTO> quotaList = list.stream().filter(ele -> StringUtils.equalsIgnoreCase(ele.getGroupType(), "q")).collect(Collectors.toList());

        Map<String, List<ChartViewFieldDTO>> map = new LinkedHashMap<>();
        map.put("dimensionList", dimensionList);
        map.put("quotaList", quotaList);
        return map;
    }

    public void copyField(Long id, Long chartId) {
        CoreDatasetTableField coreDatasetTableField = coreDatasetTableFieldRepository.findById(id).orElse(null);
        List<CoreDatasetTableField> coreDatasetTableFields = coreDatasetTableFieldRepository.findByDatasetGroupId(coreDatasetTableField.getDatasetGroupId());

        HashMap<String, String> map = new HashMap<>();
        for (CoreDatasetTableField ele : coreDatasetTableFields) {
            map.put(ele.getName(), ele.getName());
        }
        newName(map, coreDatasetTableField, coreDatasetTableField.getName());
        coreDatasetTableField.setChartId(chartId);
        coreDatasetTableField.setExtField(2);
        coreDatasetTableField.setOriginName("[" + id + "]");
        coreDatasetTableField.setId(IDUtils.snowID());
        coreDatasetTableField.setDataeaseName(TableUtils.fieldNameShort(coreDatasetTableField.getId() + "_" + coreDatasetTableField.getOriginName()));
        coreDatasetTableField.setFieldShortName(coreDatasetTableField.getDataeaseName());
        coreDatasetTableFieldRepository.saveAndFlush(coreDatasetTableField);
    }

    private void newName(HashMap<String, String> map, CoreDatasetTableField coreDatasetTableField, String name) {
        name = name + "_copy";
        if (map.containsKey(name)) {
            newName(map, coreDatasetTableField, name);
        } else {
            coreDatasetTableField.setName(name);
        }
    }

    public void deleteField(Long id) {
        coreDatasetTableFieldRepository.deleteById(id);
    }

    public void deleteFieldByChartId(Long chartId) {
        QueryWrapper<CoreDatasetTableField> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chart_id", chartId);
        coreDatasetTableFieldRepository.deleteByChartId(chartId);
    }

    public ChartBaseVO chartBaseInfo(Long id, String resourceTable) {
        ChartBasePO po = extChartViewMapper.queryChart(id, resourceTable);
        if (ObjectUtils.isEmpty(po)) return null;
        ChartBaseVO vo = BeanUtils.copyBean(new ChartBaseVO(), po);
        TypeReference<List<ChartViewFieldDTO>> tokenType = new TypeReference<>() {
        };
        vo.setXAxis(JsonUtil.parseList(po.getXAxis(), tokenType));
        vo.setXAxisExt(JsonUtil.parseList(po.getXAxisExt(), tokenType));
        vo.setYAxis(JsonUtil.parseList(po.getYAxis(), tokenType));
        vo.setYAxisExt(JsonUtil.parseList(po.getYAxisExt(), tokenType));
        vo.setExtStack(JsonUtil.parseList(po.getExtStack(), tokenType));
        vo.setExtBubble(JsonUtil.parseList(po.getExtBubble(), tokenType));
        vo.setFlowMapStartName(JsonUtil.parseList(po.getFlowMapStartName(), tokenType));
        vo.setFlowMapEndName(JsonUtil.parseList(po.getFlowMapEndName(), tokenType));
        if (StringUtils.isBlank(po.getExtColor()) || StringUtils.equals("null", po.getExtColor())) {
            vo.setExtColor(new ArrayList<>());
        } else {
            vo.setExtColor(JsonUtil.parseList(po.getExtColor(), tokenType));
        }
        vo.setExtLabel(JsonUtil.parseList(po.getExtLabel(), tokenType));
        vo.setExtTooltip(JsonUtil.parseList(po.getExtTooltip(), tokenType));
        return vo;
    }

    public DatasetTableFieldDTO createCountField(Long id) {
        DatasetTableFieldDTO dto = new DatasetTableFieldDTO();
        dto.setId(-1L);
        dto.setDatasetGroupId(id);
        dto.setOriginName("*");
        dto.setName("记录数*");
        dto.setDataeaseName("*");
        dto.setType("INT");
        dto.setChecked(true);
        dto.setColumnIndex(999);
        dto.setDeType(2);
        dto.setExtField(1);
        dto.setGroupType("q");
        return dto;
    }

    public List<ChartViewFieldDTO> transFieldDTO(List<DatasetTableFieldDTO> list) {
        return list.stream().map(ele -> {
            ChartViewFieldDTO dto = new ChartViewFieldDTO();
            if (ele == null) return null;
            BeanUtils.copyBean(dto, ele);
            dto.setDateStyle("y_M_d");
            dto.setDatePattern("date_sub");
            dto.setDateShowFormat("y_M_d");
            dto.setChartType("bar");

            if (dto.getId() == -1L || dto.getDeType() == 0 || dto.getDeType() == 1 || dto.getDeType() == 7) {
                dto.setSummary("count");
            } else {
                dto.setSummary("sum");
            }

            ChartFieldCompareDTO chartFieldCompareDTO = new ChartFieldCompareDTO();
            chartFieldCompareDTO.setType("none");
            dto.setCompareCalc(chartFieldCompareDTO);

            dto.setFormatterCfg(new FormatterCfgDTO().setUnitLanguage(Lang.isChinese() ? "ch" : "en"));

            dto.setSort("none");
            dto.setFilter(Collections.emptyList());
            return dto;
        }).collect(Collectors.toList());
    }

    public SnapshotCoreChartView transDTO2Record(ChartViewDTO dto) throws Exception {
        SnapshotCoreChartView record = new SnapshotCoreChartView();
        BeanUtils.copyBean(record, dto);
        record.setXAxis(objectMapper.writeValueAsString(dto.getXAxis()));
        record.setXAxisExt(objectMapper.writeValueAsString(dto.getXAxisExt()));
        record.setYAxis(objectMapper.writeValueAsString(dto.getYAxis()));
        record.setYAxisExt(objectMapper.writeValueAsString(dto.getYAxisExt()));
        record.setExtStack(objectMapper.writeValueAsString(dto.getExtStack()));
        record.setExtBubble(objectMapper.writeValueAsString(dto.getExtBubble()));
        record.setExtLabel(objectMapper.writeValueAsString(dto.getExtLabel()));
        record.setExtTooltip(objectMapper.writeValueAsString(dto.getExtTooltip()));
        record.setCustomAttr(objectMapper.writeValueAsString(dto.getCustomAttr()));
        if (dto.getCustomAttrMobile() != null) {
            record.setCustomAttrMobile(objectMapper.writeValueAsString(dto.getCustomAttrMobile()));
        }
        record.setCustomStyle(objectMapper.writeValueAsString(dto.getCustomStyle()));
        if (dto.getCustomAttrMobile() != null) {
            record.setCustomStyleMobile(objectMapper.writeValueAsString(dto.getCustomStyleMobile()));
        }
        record.setSenior(objectMapper.writeValueAsString(dto.getSenior()));
        record.setDrillFields(objectMapper.writeValueAsString(dto.getDrillFields()));
        record.setCustomFilter(objectMapper.writeValueAsString(dto.getCustomFilter()));
        record.setViewFields(objectMapper.writeValueAsString(dto.getViewFields()));
        record.setFlowMapStartName(objectMapper.writeValueAsString(dto.getFlowMapStartName()));
        record.setFlowMapEndName(objectMapper.writeValueAsString(dto.getFlowMapEndName()));
        record.setExtColor(objectMapper.writeValueAsString(dto.getExtColor()));
        record.setSortPriority(objectMapper.writeValueAsString(dto.getSortPriority()));
        return record;
    }

    public ChartViewDTO transRecord2DTO(CoreChartView record) {
        ChartViewDTO dto = new ChartViewDTO();
        BeanUtils.copyBean(dto, record);

        TypeReference<List<ChartViewFieldDTO>> tokenType = new TypeReference<>() {
        };
        dto.setXAxis(JsonUtil.parseList(record.getXAxis(), tokenType));
        dto.setXAxisExt(JsonUtil.parseList(record.getXAxisExt(), tokenType));
        dto.setYAxis(JsonUtil.parseList(record.getYAxis(), tokenType));
        dto.setYAxisExt(JsonUtil.parseList(record.getYAxisExt(), tokenType));
        dto.setExtStack(JsonUtil.parseList(record.getExtStack(), tokenType));
        dto.setExtBubble(JsonUtil.parseList(record.getExtBubble(), tokenType));
        dto.setExtLabel(JsonUtil.parseList(record.getExtLabel(), tokenType));
        dto.setExtTooltip(JsonUtil.parseList(record.getExtTooltip(), tokenType));
        dto.setCustomAttr(JsonUtil.parse(record.getCustomAttr(), Map.class));
        if (record.getCustomAttrMobile() != null) {
            dto.setCustomAttrMobile(JsonUtil.parse(record.getCustomAttrMobile(), Map.class));
        }
        dto.setCustomStyle(JsonUtil.parse(record.getCustomStyle(), Map.class));
        if (record.getCustomStyleMobile() != null) {
            dto.setCustomStyleMobile(JsonUtil.parse(record.getCustomStyleMobile(), Map.class));
        }
        dto.setSenior(JsonUtil.parse(record.getSenior(), Map.class));
        dto.setDrillFields(JsonUtil.parseList(record.getDrillFields(), tokenType));
        dto.setCustomFilter(JsonUtil.parseObject(record.getCustomFilter(), FilterTreeObj.class));
        dto.setViewFields(JsonUtil.parseList(record.getViewFields(), tokenType));
        dto.setFlowMapStartName(JsonUtil.parseList(record.getFlowMapStartName(), tokenType));
        dto.setFlowMapEndName(JsonUtil.parseList(record.getFlowMapEndName(), tokenType));
        dto.setExtColor(JsonUtil.parseList(record.getExtColor(), tokenType));
        dto.setSortPriority(JsonUtil.parseList(record.getSortPriority(), new TypeReference<List<SortAxis>>() {
        }));

        return dto;

    }

    public String checkSameDataSet(String viewIdSource, String viewIdTarget) {
        QueryWrapper<CoreChartView> wrapper = new QueryWrapper<>();
        wrapper.select("distinct table_id");
        wrapper.in("id", Arrays.asList(viewIdSource, viewIdTarget));
        if (coreChartViewRepository.countDistinctTableIdByIdIn(Arrays.asList(viewIdSource, viewIdTarget)) == 1) {
            return "YES";
        } else {
            return "NO";
        }

    }

    public List<ViewSelectorVO> viewOption(Long resourceId) {
        List<ViewSelectorVO> result = extChartViewMapper.queryViewOption(resourceId);
        DataVisualizationInfo dvInfo = dataVisualizationInfoRepository.findById(String.valueOf(resourceId)).orElse(null);
        if (dvInfo != null && !CollectionUtils.isEmpty(result)) {
            String componentData = dvInfo.getComponentData();
            return result.stream().filter(item -> componentData.indexOf(String.valueOf(item.getId())) > 0).toList();
        } else {
            return result;
        }
    }

    public ChartViewDTO findChartViewAround(String viewId) {
        return extChartViewMapper.findChartViewAround(viewId);
    }
}
