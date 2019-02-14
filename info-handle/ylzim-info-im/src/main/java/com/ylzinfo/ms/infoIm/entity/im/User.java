package com.ylzinfo.ms.infoIm.entity.im;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "SYS_USER")
@Table
@Data
public class User {
	@Id
	@Column(name = "ID")
	private Long id;
	@Column(name = "name")
	private String name;

}
