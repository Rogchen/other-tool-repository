package com.ylzinfo.ms.infoIm.mapper.imMapper;

import com.ylzinfo.ms.infoIm.entity.im.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2018/10/17 14:20
 **/
public interface UserMapper extends CrudRepository<User, Long> {

    User getById(long id);
}
