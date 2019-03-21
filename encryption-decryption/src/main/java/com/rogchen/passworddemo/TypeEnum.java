package com.rogchen.passworddemo;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/3/21 16:06
 **/
public enum TypeEnum {
    AES("98iu7h90nbfe46ha"),MD5("23423423dsfs");

    private String desc;

    public String getDesc() {
        return desc;
    }

    private TypeEnum(String desc) {
        this.desc = desc;
    }
}
