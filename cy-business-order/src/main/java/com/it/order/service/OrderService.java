package com.it.order.service;

import com.it.common.api.model.order.CyOrder;

import java.util.List;

/**
 * @Author Cying
 * @Date 2022/6/27 14:18
 * @Description
 */
public interface OrderService {

    void insertOrder(CyOrder cyOrder);

    List<CyOrder> queryList();

    void updateById(String id);
}
