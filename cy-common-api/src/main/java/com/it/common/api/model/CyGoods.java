package com.it.common.api.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @Author Cying
 * @Date 2022/7/12 11:09
 * @Description
 */
@Data
public class CyGoods {
    @Id
    private String goodsId;
    private String name;
    private String type;
    private Integer number;
    private Integer stock;
    private Integer price;
}
