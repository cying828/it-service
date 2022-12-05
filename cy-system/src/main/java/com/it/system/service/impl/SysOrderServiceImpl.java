package com.it.system.service.impl;

import com.it.common.api.feign.RemoteOrderService;
import com.it.common.api.model.order.CyOrder;
import com.it.common.core.constant.SecurityConstants;
import com.it.system.mapper.AccountMapper;
import com.it.system.mapper.GoodsMapper;
import com.it.system.mapper.OrderMapper;
import com.it.system.service.SysOrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @Author Cying
 * @Date 2022/7/12 12:15
 * @Description
 */
@Slf4j
@Service
public class SysOrderServiceImpl implements SysOrderService {

    @Resource
    private RemoteOrderService remoteOrderService;

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private GoodsMapper goodsMapper;

//    @Resource
//    private OrderMapper orderMapper;

    @Override
    @GlobalTransactional(name = "cy-test-order",rollbackFor = Exception.class)
    public void createOrder() {

        String xid = RootContext.getXID();
        System.out.println("======system========="+xid);

        CyOrder cyOrder = new CyOrder();
        cyOrder.setId(UUID.randomUUID().toString().replace("-","").substring(0,28));
        cyOrder.setName(Thread.currentThread().getName());
        int num = new Random().nextInt(4);
        cyOrder.setOrderNum(num);
        cyOrder.setOrderType("1");
        cyOrder.setState("0");
        cyOrder.setCreateTime(LocalDateTime.now());
        //创建订单
        remoteOrderService.insertOrder(cyOrder,SecurityConstants.FROM_IN);
        int count = 1;
        int price = 20;
        //去支付
        accountMapper.payMoney(price*count,10020);

        //支付成功 减库存 修改订单状态
        goodsMapper.update(count);

        CyOrder order = new CyOrder();
        order.setId(cyOrder.getId());
        order.setState("1");
        remoteOrderService.updateById(cyOrder.getId(), SecurityConstants.FROM_IN);
        log.info("执行成功================");
    }

    @Override
    public List<CyOrder> getOrderList() {
        return remoteOrderService.getOrderList(SecurityConstants.FROM_IN);
    }
}
