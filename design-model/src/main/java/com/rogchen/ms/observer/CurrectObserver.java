package com.rogchen.ms.observer;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/4/23 11:33
 **/
public class CurrectObserver implements Observer {
    @Override
    public void response() {
        System.out.println("具体观察者1作出反应！");
    }
}
