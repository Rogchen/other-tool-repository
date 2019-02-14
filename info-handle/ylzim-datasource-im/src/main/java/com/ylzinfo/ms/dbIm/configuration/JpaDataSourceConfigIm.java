package com.ylzinfo.ms.dbIm.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @Description: jpa配置
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2018/11/9 15:20
 **/
@Configuration
@Import(DatasourceIm.class)
public class JpaDataSourceConfigIm {

    @Autowired
    private DatasourceIm datasourceIm;

    /**
     * @methodDesc: 功能描述:(配置test２数据库)
     */
    @Bean(name = "primaryDataSource")
    @Primary
    public DataSource ImDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        //设置数据源的属性
        dataSource.setUrl(datasourceIm.getUrl());
        dataSource.setDriverClassName(datasourceIm.getDriverClassName());
        dataSource.setPassword(datasourceIm.getPassword());
        dataSource.setUsername(datasourceIm.getUsername());
        return dataSource;
    }
}
