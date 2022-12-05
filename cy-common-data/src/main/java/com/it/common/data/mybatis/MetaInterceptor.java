package com.it.common.data.mybatis;

import cn.hutool.core.util.ArrayUtil;
import com.it.common.security.service.cyUser;
import com.it.common.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * @author junyang
 * @date 2019/9/10
 */
@Slf4j
@AllArgsConstructor
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class MetaInterceptor implements Interceptor {

    /**
     * 创建时间
     */
    private static final String CREATE_TIME = "createTime";
    /**
     * 更新时间
     */
    private static final String UPDATE_TIME = "updateTime";

    /**
     * 创建人
     */
    protected static final String CREATE_ID = "createId";
    /**
     * 更新人
     */
    protected static final String UPDATE_ID = "updateId";

    private static final String PARAM_1 = "param1";
    private static final String ET = "et";

    @Override
    @SneakyThrows
    public Object intercept(Invocation invocation) {
        // 获取新增或修改的对象参数
        Object parameter = invocation.getArgs()[1];
        // 获取对象中所有的私有成员变量（对应表字段）
        Field[] declaredFields = parameter.getClass().getDeclaredFields();
        if (parameter.getClass().getSuperclass() != null) {
            Field[] superField = parameter.getClass().getSuperclass().getDeclaredFields();
            declaredFields = ArrayUtil.addAll(superField, declaredFields);
        }
        // mybatis plus判断
        boolean plus = parameter.getClass().getDeclaredFields().length == 1 && parameter.getClass().getDeclaredFields()[0].getName().equals("serialVersionUID");

        //兼容mybatis plus
        if (plus) {
            Map<String, Object> updateParam = (Map<String, Object>) parameter;
            Class<?> updateParamType = getOperateObject(updateParam).getClass();
            declaredFields = updateParamType.getDeclaredFields();
            if (updateParamType.getSuperclass() != null) {
                Field[] superField = updateParamType.getSuperclass().getDeclaredFields();
                declaredFields = ArrayUtil.addAll(declaredFields, superField);
            }
        }
        String fieldName;
        cyUser user = SecurityUtils.getUser();
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        for (Field field : declaredFields) {
            fieldName = field.getName();
            if (Objects.equals(CREATE_TIME, fieldName)) {
                if (SqlCommandType.INSERT.equals(mappedStatement.getSqlCommandType())) {
                    field.setAccessible(true);
                    field.set(parameter, LocalDateTime.now());
                }
            }
            if (Objects.equals(CREATE_ID, fieldName)) {
                if (SqlCommandType.INSERT.equals(mappedStatement.getSqlCommandType())) {
                    field.setAccessible(true);
                    field.set(parameter, user == null ? "admin" : user.getId());
                }
            }
            if (Objects.equals(UPDATE_TIME, fieldName)) {
                if (SqlCommandType.INSERT.equals(mappedStatement.getSqlCommandType())) {
                    field.setAccessible(true);
                    field.set(parameter, LocalDateTime.now());
                }
                if (plus && SqlCommandType.UPDATE.equals(mappedStatement.getSqlCommandType())) {
                    field.setAccessible(true);
                    Map<String, Object> updateParam = (Map<String, Object>) parameter;
                    field.set(getOperateObject(updateParam), LocalDateTime.now());
                }
                if (!plus && SqlCommandType.UPDATE.equals(mappedStatement.getSqlCommandType())) {
                    field.setAccessible(true);
                    field.set(parameter, LocalDateTime.now());
                }
            }
            if (Objects.equals(UPDATE_ID, fieldName)) {
                field.setAccessible(true);
                if (plus && SqlCommandType.UPDATE.equals(mappedStatement.getSqlCommandType())) {
                    field.setAccessible(true);
                    Map<String, Object> updateParam = (Map<String, Object>) parameter;
                    field.set(getOperateObject(updateParam), user == null ? null : user.getId());
                }
                if (SqlCommandType.INSERT.equals(mappedStatement.getSqlCommandType())) {
                    field.setAccessible(true);
                    field.set(parameter, user == null ? null : user.getId());
                }
                if (!plus && SqlCommandType.UPDATE.equals(mappedStatement.getSqlCommandType())) {
                    field.setAccessible(true);
                    field.set(parameter, user == null ? null : user.getId());
                }
            }
        }
        return invocation.proceed();
    }

    private Object getOperateObject(Map<String, Object> map) {
        if (map.containsKey(ET)) {
            return map.get(ET);
        }
        return map.get(PARAM_1);
    }

    /**
     * 生成拦截对象的代理
     *
     * @param target 目标对象
     * @return 代理对象
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
