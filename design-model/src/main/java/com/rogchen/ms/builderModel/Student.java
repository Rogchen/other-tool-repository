package com.rogchen.ms.builderModel;

import java.util.Date;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/2/13 10:39
 **/

public class Student {

    private int age;
    private String username;
    private Date birthday;
    private String product;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Student(Builder builder) {
        this.age = builder.age;
        this.birthday = builder.birthday;
        this.username = builder.username;
        this.product = builder.product;
    }

    public static final class Builder {
        private int age;
        private String username;
        private Date birthday;
        private String product;

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder birthday(Date birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder product(String product) {
            this.product = product;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}
