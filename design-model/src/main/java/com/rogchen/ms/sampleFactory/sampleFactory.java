package com.rogchen.ms.sampleFactory;

/**
 * @Description: 简单工厂模式实现
 * <p>简单工厂模式跟多态区别：多态是子父类之间关系，简单工厂模式是由入参决定实例化对象</p>
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2018/12/7 10:02
 **/
public class sampleFactory {

    /**
    * @Description 方法一、简单工厂-由入参决定实例化
    * @Author Rogchen
    * @Date 10:06 2018/12/7
    * @Param [type]
    * @Return void
    **/
    public static void getSay(String type) {
        if("1".equals(type)){
            Human h = new Man();
            h.say();
        }else {
            Human h = new Woman();
            h.say();
        }
    }

    /**
    * @Description 方法二、通过入参反射
    * @Author Rogchen
    * @Date 10:13 2018/12/7
    * @Param cs class
    * @Return void
    **/
    public static Human getClass(Class<?> cs) {
        Human h = null;
        try {
            h = (Human) Class.forName(cs.getName()).newInstance();
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

    public static void main(String[] args) {
        //单个方法
        getSay("1");
        //通过反射
        Human h = getClass(Woman.class);
        h.say();
    }
}
