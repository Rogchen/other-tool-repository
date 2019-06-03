package com.rogchen.ms.structure.adapter.object_adapter;

/**
 * @Description: 必须先定义一个需要适配的类，可以通过定义一个统一标准接口来做适配
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/5/6 16:03
 **/
public class Adapter implements Targetable {

    private Source source;

    public Adapter(Source source){
        this.source = source;
    }

    @Override
    public void method() {
        source.specialMethod();
    }

    public static void main(String[] args) {
        Targetable targetable = new Adapter(new Source());
        targetable.method();

        Targetable ab = new Concreate();
        ab.method();


    }
}
