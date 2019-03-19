package com.ylzinfo.ms.infoIm.mapper.usercenterMapper;

import com.ylzinfo.ms.infoIm.entity.Uc.PersonAuth;
import org.springframework.data.repository.CrudRepository;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2018/10/17 14:20
 **/
public interface PersonMapper extends CrudRepository<PersonAuth, Long> {

    PersonAuth getById(long id);
}
