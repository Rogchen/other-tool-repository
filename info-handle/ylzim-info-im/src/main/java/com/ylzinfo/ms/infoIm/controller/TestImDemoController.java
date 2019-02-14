package com.ylzinfo.ms.infoIm.controller;

import com.ylzinfo.ms.infoIm.entity.Uc.PersonAuth;
import com.ylzinfo.ms.infoIm.entity.im.User;
import com.ylzinfo.ms.infoIm.mapper.imMapper.UserMapper;
import com.ylzinfo.ms.infoIm.mapper.usercenterMapper.PersonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2018/10/17 14:26
 **/
@RestController
@Slf4j
public class TestImDemoController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PersonMapper personMapper;

    @RequestMapping("test")
    public String test1() {
        User map = userMapper.getById(1l);
        System.out.print(map);
        PersonAuth ob = personMapper.getById(10020l);
        log.info(ob.toString());
        return "test";
    }
}
