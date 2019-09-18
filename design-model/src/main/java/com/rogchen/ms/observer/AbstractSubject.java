package com.rogchen.ms.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 抽象目标类
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/4/23 11:27
 **/
public abstract class AbstractSubject {

    protected List<Observer>observers = new ArrayList<>();

    //添加观察者
    public void add(Observer observer){
        observers.add(observer);
    }

    public void remove(Observer observer){
        observers.remove(observer);
    }

    //通知观察者
    public abstract void notifyObserver();

}
