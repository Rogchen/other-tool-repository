package com.rogchen.ms.sampleFactory;

/**
 * @Description: 猫科工厂模式
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2018/12/7 10:45
 **/
public class sampleFactoryCat {

    public static void factory1(String c1) {
        if ("1".equals(c1)) {
            CatKe ck = new cat();
            ck.setName("小猫");
            ck.tell();
        }else {
            CatKe c = new caten();
            c.setName("老虎");
            c.tell();
        }

    }

    /**
     *  反射实现
     * @param cs
     */
    public static CatKe getClass(Class<?> cs) {
        CatKe h = null;
        try {
            h = (CatKe) Class.forName(cs.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            System.out.println("实例化errer");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("get error");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("case error");
        }
        return h;
    }

    /**
    * @Description 
    * @Author Rogchen
    * @Date 10:54 2018/12/7
    * @Param [args]
    * @Return void
    **/
    public static void main(String[] args) {
        factory1("1");

        CatKe ck = getClass(cat.class);
        ck.setName("哈哈哈");
        ck.tell();
    }

}


