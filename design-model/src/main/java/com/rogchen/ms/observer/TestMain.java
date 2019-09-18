package com.rogchen.ms.observer;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/4/23 11:34
 **/
public class TestMain {

    public static void main(String[] args) {
        AbstractSubject subject = new CurrectSubject();
        Observer o1 = new CurrectObserver();
        Observer o2 = new CurrectObserver2();
        subject.add(o1);
        subject.add(o2);
        subject.notifyObserver();
    }
}
