package com.it.common.api.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @Author Cying
 * @Date 2022/7/12 11:09
 * @Description
 */
@Data
public class CyAccount {
    @Id
    private String id;
    private String name;
    private Integer amount;
    private Integer surplus;
}
