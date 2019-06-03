package com.ylzinfo.ms.common;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//@Import(RateLimitAutoConfiguration.class)
@Documented
@Inherited
public @interface EnableRateLimit {
}