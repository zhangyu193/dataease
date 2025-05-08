package io.dataease.visualization.dao.ext.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.dataease.visualization.dao.ext.po.VisualizationNodePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Set;

@Mapper
public interface CoreVisualiationExtMapper {

    @Select("""
            select id, name, pid, node_type, mobile_layout as extraFlag, status as extraFlag1 from data_visualization_info
            ${ew.customSqlSegment}
            """)
    List<VisualizationNodePO> queryNodes(@Param("ew") QueryWrapper<Object> queryWrapper);

    @Select("select id from data_visualization_info where pid = #{pid} and delete_flag = 0")
    List<Long> queryChildrenId(@Param("pid") Long pid);

}
