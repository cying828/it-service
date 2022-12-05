package com.it.system.service;

import com.it.common.api.model.order.CyOrder;

import java.util.List;

/**
 * @Author Cying
 * @Date 2022/7/12 12:13
 * @Description
 */
public interface SysOrderService {
    public void createOrder();

    List<CyOrder> getOrderList();

}
