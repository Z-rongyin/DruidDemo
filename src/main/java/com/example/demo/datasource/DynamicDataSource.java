package com.example.demo.datasource;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 动态切换数据库
 */
public class DynamicDataSource  extends AbstractRoutingDataSource {

    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceType = DynamicDataSourceContextHolder.getDataSourceType();
        //如果为空就用主库
        if (StringUtils.isBlank(dataSourceType)) {
            dataSourceType=DataSourceType.MASTER.name();
        }
        return dataSourceType;
    }
}
