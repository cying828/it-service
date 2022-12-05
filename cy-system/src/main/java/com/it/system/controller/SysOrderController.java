package com.it.system.controller;

import com.it.common.api.model.order.CyOrder;
import com.it.system.service.SysOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Cying
 * @Date 2022/6/27 14:55
 * @Description
 */
@RestController
@RequestMapping("/sysOrder")
public class SysOrderController {



    @Autowired
    private SysOrderService sysOrderService;

    @RequestMapping("/add")
    public void addOrder() {

    }

    @GetMapping("/orderList")
    public List<CyOrder> getList() {
        return sysOrderService.getOrderList();
    }

    @RequestMapping("/create")
    public void createOrder() {
        sysOrderService.createOrder();
    }
}
