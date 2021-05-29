package com.suchness.realscene.common.service.system;

import com.suchness.realscene.common.dao.system.LoginLogRepository;
import com.suchness.realscene.common.entity.system.LoginLog;
import com.suchness.realscene.common.service.BaseService;
import com.suchness.realscene.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * @Author hejunfeng
 * @Date 14:31 2020/8/28 0028
 * @Description 登录日志service
 **/
@Service
public class LoginLogService extends BaseService<LoginLog,Integer, LoginLogRepository> {
    @Autowired
    LoginLogRepository loginLogRepository;

    public Map queryLog(Long userId){
        Object obj = loginLogRepository.queryLog(userId.intValue());
        if (obj != null){
            Object[] arr = (Object[])obj;
            Map<String , Object> map = new LinkedHashMap<>();
            map.put("date", DateUtils.dateExactTime((Date) arr[0]));
            map.put("count", Integer.parseInt(((BigInteger)arr[1]).toString()));
            return map;
        }else{
            Map<String , Object> map = new LinkedHashMap<>();
            map.put("count", 1);
            return map;
        }
    }
}
