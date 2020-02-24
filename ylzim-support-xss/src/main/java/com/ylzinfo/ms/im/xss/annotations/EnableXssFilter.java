package com.ylzinfo.ms.im.xss.annotations;

import com.ylzinfo.ms.im.xss.configuration.XssFilterRegister;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/9/12 16:04
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(XssFilterRegister.class)
public @interface EnableXssFilter {
}
