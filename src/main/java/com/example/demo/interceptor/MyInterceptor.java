package com.example.demo.interceptor;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo.datasource.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

/**
 * mybatis拦截
 * 拦截SQL查询语句，将其设置为从库
 */

@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
@Component
@Slf4j
public class MyInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //如果未指定数据源，则设置为从库
        if (StringUtils.isBlank(DynamicDataSourceContextHolder.getDataSourceType())) {
            DynamicDataSourceContextHolder.setSlave();
        }
        return invocation.proceed();
    }
}
