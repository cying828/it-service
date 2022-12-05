package com.it.common.api.sentinel;

import com.it.common.api.feign.RemoteOrderService;
import com.it.common.api.model.order.CyOrder;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Cying
 * @Date 2022/6/27 18:19
 * @Description
 */
@Component
public class OrderServiceFallBack implements FallbackFactory<RemoteOrderService> {
    @Override
    public RemoteOrderService create(Throwable throwable) {
        return new RemoteOrderService() {
            @Override
            public void insertOrder(CyOrder cyOrder,String from) {
                CyOrder order = new CyOrder();
                order.setId("-1");
            }

            @Override
            public List<CyOrder> getOrderList(String from) {

                throwable.printStackTrace();

                List<CyOrder> list = new ArrayList<>();
                CyOrder order = new CyOrder();
                order.setId("-1");
                list.add(order);
                return list;
            }

            @Override
            public void updateById(String id,String from) {

            }
        };
    }
}
