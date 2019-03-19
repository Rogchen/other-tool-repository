package com.ylzinfo.ms.dbUsercenter.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

/**
 * @Description: jpa配置
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2018/11/9 15:20
 **/
@Configuration
@Import(DatasourceUc.class)
public class JpaDataSourceConfigUc {

    @Autowired
    private DatasourceUc datasourceUc;

    /**
     * @methodDesc: 功能描述:(配置test２数据库)
     */
    @Bean(name = "secondaryDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.dbIm")
    public DataSource ImDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        //设置数据源的属性
        dataSource.setDriverClassName(datasourceUc.getDriverClassName());
        dataSource.setUrl(datasourceUc.getUrl());
        dataSource.setPassword(datasourceUc.getPassword());
        dataSource.setUsername(datasourceUc.getUsername());
        return dataSource;
//        return  DataSourceBuilder.create().build();
    }


}
