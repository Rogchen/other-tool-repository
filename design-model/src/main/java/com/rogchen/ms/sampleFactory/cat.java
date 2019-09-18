package com.rogchen.ms.sampleFactory;

/**
 * @Description: 猫
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2018/12/7 10:42
 **/
public class cat extends CatKe {
    @Override
    public void tell() {
        System.out.println(this.getName()+":my name is cat!");
    }
}
