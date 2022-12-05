package com.it.common.api.feign;

import com.it.common.api.model.order.CyOrder;
import com.it.common.api.sentinel.OrderServiceFallBack;
import com.it.common.core.constant.SecurityConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Cying
 * @Date 2022/6/27 14:57
 * @Description
 */
@FeignClient(contextId = "remoteOrderService",name = "cy-business-order")
//@FeignClient(contextId = "remoteOrderService",name = "cy-business-order",fallbackFactory = OrderServiceFallBack.class)
public interface RemoteOrderService {

    @PostMapping("/order/add")
    void insertOrder(@RequestBody CyOrder cyOrder, @RequestHeader(SecurityConstants.FROM) String from);

    @GetMapping("/order/list")
    List<CyOrder> getOrderList( @RequestHeader(SecurityConstants.FROM) String from);

    @PostMapping("/order/update/{id}")
    void updateById(@PathVariable("id") String id, @RequestHeader(SecurityConstants.FROM) String from);
}
