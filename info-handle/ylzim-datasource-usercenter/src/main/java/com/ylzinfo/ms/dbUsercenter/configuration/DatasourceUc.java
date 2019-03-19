package com.ylzinfo.ms.dbUsercenter.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2018/10/17 16:07
 **/
@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource.dbUsercenter")
public class DatasourceUc {
    public String url;

    public String username;

    public String password;

    public String driverClassName;


}
