package com.example.demo.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 切面
 */
@Slf4j
@Aspect
@Component
@Order(1)
public class DataSourceSwitchAOP {

    @Before("@annotation(com.example.demo.datasource.Master) && !@annotation(com.example.demo.datasource.Slave)")
    public void setWriteDataSourceType() {
        DynamicDataSourceContextHolder.setMaster();
    }

    @Before("@annotation(com.example.demo.datasource.Slave) && !@annotation(com.example.demo.datasource.Master)")
    public void setReadDataSourceType() {
        DynamicDataSourceContextHolder.setSlave();
    }

    @After("(@annotation(com.example.demo.datasource.Slave) && !@annotation(com.example.demo.datasource.Master)) || (@annotation(com.example.demo.datasource.Master) && !@annotation(com.example.demo.datasource.Slave))")
    public void clearDataSourceType() {
        DynamicDataSourceContextHolder.clearDataSourceType();
    }

}

