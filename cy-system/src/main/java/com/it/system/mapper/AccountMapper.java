package com.it.system.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @Author Cying
 * @Date 2022/7/12 12:19
 * @Description
 */
public interface AccountMapper {

    void payMoney(@Param("money") int i,@Param("id") int id);
}
