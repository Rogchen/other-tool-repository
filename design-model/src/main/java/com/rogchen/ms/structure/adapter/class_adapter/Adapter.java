package com.rogchen.ms.structure.adapter.class_adapter;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/5/6 15:22
 **/
public class Adapter extends Source implements Targetable {

    @Override
    public void method() {
        //将标准接口适配到已存在的接口
        super.publicMethod();
    }



    public static void main(String[] args) {
        //适配器-将我们需要的接口适配到已存在的接口上去。
        Targetable targetable = new Adapter();
        targetable.method();
        //普通实现类-无法实现已存在但是不是我们需要的接口
        Targetable able = new Concreate();
        able.method();
    }
}
