package com.ylzinfo.ms;

import gov.zwfw.iam.client.TacsHttpClient;
import gov.zwfw.iam.dict.client.TacsDictClient;
import gov.zwfw.iam.dict.request.UrlRequest;
import gov.zwfw.iam.dict.response.UrlResult;
import gov.zwfw.iam.exception.TacsException;
import gov.zwfw.iam.user.client.TacsNaturalClient;
import gov.zwfw.iam.user.request.UserRequest;
import gov.zwfw.iam.user.response.UserResult;
import org.junit.Test;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/6/19 16:29
 **/
public class GovIamTest {

    private void init(){
        //初始化客户端
        TacsHttpClient.init("/cer","http://112.35.18.164:38050/tacs-agent-web");
//        TacsHttpClient.init("/cer","http://112.35.18.166:8050/tacs-agent-web");

    }

    @Test
    public void 获取地址_getUrls() throws TacsException {
        init();
        //获取客户端
        TacsDictClient urlClient = TacsDictClient.getInstance();
        //发起调用
        UrlRequest urlRequest = new UrlRequest ();
        urlRequest.setKeyType("ACL000");
        //获取结果
        UrlResult urlResult = urlClient.getUrls(urlRequest);
        System.out.println(urlResult);
    }

    @Test
    public void 自然人隐形登录地址_login() throws TacsException {
        init();
        //获取客户端
        TacsNaturalClient userClient = TacsNaturalClient.getInstance();
        //发起调用
        UserRequest userRequest = new UserRequest();
        userRequest.setAuthCode("01");
        userRequest.setCertKey("01");
        //获取结果
        UserResult userResult = userClient.getNaturalToken(userRequest);
    }
}
