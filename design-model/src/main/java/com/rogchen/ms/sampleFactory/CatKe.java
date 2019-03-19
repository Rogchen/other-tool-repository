package com.rogchen.ms.sampleFactory;

/**
 * @Description: 猫科-通过继承方式
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2018/12/7 10:41
 **/
public abstract class CatKe {

    private String name;

    public abstract void tell();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
