package com.rogchen.ms.structure.adapter.interface_adapter;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/5/6 16:19
 **/
public class TestMain {

    public static void main(String[] args) {
        Sourceable sourceable = new SourceDetail1();
        sourceable.method1();

        Sourceable ab2 = new SourceDetail2();
        ab2.method2();
    }
}
