package com.rogchen.ms.observer;


/**
 * @Description: 目标具体
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/4/23 11:30
 **/
public class CurrectSubject extends AbstractSubject{

    @Override
    public void notifyObserver() {
        System.out.println("具体目标发生改变...");
        System.out.println("--------------");

        for(Object obs:observers)
        {
            ((Observer)obs).response();
        }
    }
}
