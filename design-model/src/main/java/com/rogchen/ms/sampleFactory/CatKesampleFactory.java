package com.rogchen.ms.sampleFactory;

/**
 * @Description: 多个方法的简单工厂模式-创建需要实例化方法
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/3/20 15:35
 **/
public class CatKesampleFactory {

    public CatKe catSpell(){
        return new cat();
    }

    public CatKe catenSpell(){
        return new caten();
    }

    //。。。其它方法和实例化


    //将方法设置为静态就不需要手动在进行实例化。
    public static CatKe catTell(){
        return new cat();
    }

    public static CatKe catenTell(){
        return new caten();
    }

    /**
     * 通过调用工厂模式里面的方法实例化除你需要的对象
     * @param args
     */
    public static void main(String[] args) {
        //根据需要再去初始化
        CatKesampleFactory factory = new CatKesampleFactory();
        CatKe sender = factory.catSpell();
        sender.setName("老猫");
        sender.tell();

        CatKe sd = factory.catenSpell();
        sd.setName("老虎");
        sd.tell();

        //启动时初始化
        CatKe ck = CatKesampleFactory.catenTell();
        ck.setName("汤姆");
        ck.tell();

    }
}
