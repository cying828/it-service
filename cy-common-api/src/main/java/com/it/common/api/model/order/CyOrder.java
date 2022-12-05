package com.it.common.api.model.order;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

/**
 * @Author Cying
 * @Date 2022/6/27 14:59
 * @Description
 */
@Data
public class CyOrder {
    @Id
    private String id;
    private String name;
    private Integer orderNum;
    private String orderType;
    /**
     * 0- 未支付  1-已支付
     */
    private String state;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
