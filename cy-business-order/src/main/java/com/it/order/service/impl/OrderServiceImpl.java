package com.it.order.service.impl;

import com.it.common.api.model.order.CyOrder;
import com.it.order.mapper.OrderMapper;
import com.it.order.service.OrderService;
import io.seata.core.context.RootContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Cying
 * @Date 2022/6/27 14:18
 * @Description
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public void insertOrder(CyOrder cyOrder) {
        String xid = RootContext.getXID();
        System.out.println("======order1========="+xid);
        orderMapper.insert(cyOrder);
    }

    @Override
    public List<CyOrder> queryList() {
        return orderMapper.queryList();
    }

    @Override
    public void updateById(String id) {

        String xid = RootContext.getXID();
        System.out.println("======order2========="+xid);

        CyOrder cyOrder = new CyOrder();
        cyOrder.setId(id);
        cyOrder.setState("1");
        orderMapper.update(cyOrder);
    }
}
