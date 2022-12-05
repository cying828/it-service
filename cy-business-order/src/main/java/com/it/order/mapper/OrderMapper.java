package com.it.order.mapper;

import com.it.common.api.model.order.CyOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Cying
 * @Date 2022/6/27 14:19
 * @Description
 */
public interface OrderMapper {
    void insert(@Param("cyOrder") CyOrder cyOrder);

    List<CyOrder> queryList();

    void update(@Param("order") CyOrder cyOrder);
}
