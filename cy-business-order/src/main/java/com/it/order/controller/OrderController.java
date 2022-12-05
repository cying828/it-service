package com.it.order.controller;

import com.it.common.api.model.order.CyOrder;
import com.it.common.security.annotation.Inner;
import com.it.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Cying
 * @Date 2022/6/27 14:17
 * @Description
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderServiceImpl;

    @PostMapping("/add")
    @Inner
    public void add(@RequestBody CyOrder cyOrder){
        try {

            orderServiceImpl.insertOrder(cyOrder);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/list")
    @Inner
    public List<CyOrder> queryList() {

        return orderServiceImpl.queryList();
    }

    @PostMapping("/update/{id}")
    @Inner
    public void updateById(@PathVariable("id") String id) {
        orderServiceImpl.updateById(id);
    }

}
