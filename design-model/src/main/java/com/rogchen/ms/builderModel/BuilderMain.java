package com.rogchen.ms.builderModel;

import java.util.Date;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/2/13 11:06
 **/
public class BuilderMain {

    public static void main(String[] args) {
        Student student = new Student.Builder().age(20).birthday(new Date()).build();
        System.out.println(student.getAge());
    }
}
