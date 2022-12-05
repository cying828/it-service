package com.it.common.core.constant;

/**
 * @author tony
 * @date 2019-04-28
 * <p>
 * 缓存的key 常量
 */
public interface CacheConstants {

    /**
     * 用户信息缓存
     */
    String USER_DETAILS = "user_details";


    /**
     * oauth 客户端信息
     */
    String CLIENT_DETAILS_KEY = "cy_oauth:client:details";

    /**
     * 菜单信息缓存
     */
    String MENU_DETAILS = "menu_details";

    /**
     * spring boot admin 事件key
     */
    String EVENT_KEY = "event_key";

    /**
     * 路由存放
     */
    String ROUTE_KEY = "gateway_route_key";

    /**
     * 字典缓存
     */
    String DICT_DETAILS = "dict_details";

    /**
     * 参数缓存
     */
    String PARAMS_DETAILS = "params_details";

    /**
     * 角色缓存
     */
    String ROLE_DETAILS = "role_details";

    /**
     * 角色缓存
     */
    String ROLE_EQUIP = "role_equip";

    /**
     * 小程序菜单缓存
     */
    String WECHART_MENU = "wechart_menu";

    /**
     * 用户登录次数缓存
     */
    String LOGIN_COUNT = "cy_login_count:loginname-";

}
