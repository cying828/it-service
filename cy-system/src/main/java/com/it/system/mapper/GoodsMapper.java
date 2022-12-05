package com.it.system.mapper;


import org.apache.ibatis.annotations.Param;

/**
 * @Author Cying
 * @Date 2022/7/12 12:20
 * @Description
 */
public interface GoodsMapper {
    void update(@Param("count") int count);
}
