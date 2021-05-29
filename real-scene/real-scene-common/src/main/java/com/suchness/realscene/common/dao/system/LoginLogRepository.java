package com.suchness.realscene.common.dao.system;

import com.suchness.realscene.common.dao.BaseRepository;
import com.suchness.realscene.common.entity.system.LoginLog;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

/**
 * @Author hejunfeng
 * @Date 14:52 2020/8/28 0028
 * @Description 登陆日志类
 **/
public interface LoginLogRepository extends BaseRepository<LoginLog, Integer> {

    @Query(value = "SELECT * FROM(" +
            "(SELECT a.create_time FROM t_sys_login_log as a WHERE a.userid = :userid ORDER BY a.create_time DESC limit 1,1)a," +
            "(SELECT count(*) as count FROM t_sys_login_log as a WHERE a.userid = :userid)b)", nativeQuery = true)
    @Async
    Object queryLog(@Param("userid") Integer userid);
}
