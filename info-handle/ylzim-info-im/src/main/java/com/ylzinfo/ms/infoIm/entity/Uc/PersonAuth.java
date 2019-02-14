package com.ylzinfo.ms.infoIm.entity.Uc;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description: 个人信息表
 * @Author: Wenqw
 * @Created Date: 2018/4/4 09:11
 **/
@Table
@Entity(name = "SYS_PERSON")
@Data
public class PersonAuth {
    @Id
    private Long id;
    @Column(name = "name")
    private String name;
}
