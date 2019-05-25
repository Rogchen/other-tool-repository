package com.rogchen.asyc.config;

import com.rogchen.asyc.service.BaiduService;
import com.rogchen.asyc.service.BilibiliService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/5/25 16:30
 **/
@Component
@Log
public abstract class CommonService {

    @Autowired
    protected BaiduService baiduService;
    @Autowired
    protected BilibiliService bilibiliService;

    public abstract String buyTicket(String idNo) throws ExecutionException, InterruptedException;
}
