package com.it.system.mapper;

import com.it.common.api.model.SysRouteConf;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Cying
 * @Date 2022/7/1 23:10
 * @Description
 */
public interface SysRouteConfMapper {
    List<SysRouteConf> selectList();

    void remove(@Param("route") SysRouteConf condition);

    void saveList(@Param("routeConfList") List<SysRouteConf> routeConfList);
}
