package com.ylzinfo.ms.im.xss.configuration;

import com.ylzinfo.ms.im.xss.XssFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import java.util.*;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 19-9-16 16:05
 **/
@Configuration
@EnableConfigurationProperties(XssProperties.class)
public class XssFilterRegister {


    @Autowired
    private XssProperties xssProperties;

    @Bean
    public FilterRegistrationBean sysVisitFilterBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new XssFilter());
        registrationBean.addInitParameter("targetFilterLifecycle", "true");
        registrationBean.addUrlPatterns("/*");
        List<String> xssExcludes = new ArrayList<>(Arrays.asList("/*.js", "/*.gif", "/*.jpg", "/*.png", "*/.css", "/*.ico"));
        List<String> addExcludes = xssProperties.getExclude();
        if (xssExcludes == null) {
            xssExcludes = new ArrayList<>();
        }
        xssExcludes.addAll(addExcludes);
        List<String> includes = xssProperties.getInclude();
        if (includes == null) {
            includes = new ArrayList<>();
        }registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        registrationBean.setOrder(1);
        registrationBean.setEnabled(true);
        Map<String, String> initParameters = new HashMap();
        initParameters.put("excludes", String.join(",", xssExcludes));
        initParameters.put("includes", String.join(",", includes));
        registrationBean.setInitParameters(initParameters);
        return registrationBean;
    }
}
