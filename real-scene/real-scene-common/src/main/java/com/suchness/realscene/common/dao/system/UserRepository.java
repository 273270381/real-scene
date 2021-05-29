package com.suchness.realscene.common.dao.system;

import com.suchness.realscene.common.dao.BaseRepository;
import com.suchness.realscene.common.entity.system.User;

/***
 *  author: wch
 *  create_time : 2020/6/12 14:12
 *******/
public interface UserRepository extends BaseRepository<User,Long> {
    User findByAccount(String account);

    User findByAccountAndStatusNot(String account, Integer status);

}
