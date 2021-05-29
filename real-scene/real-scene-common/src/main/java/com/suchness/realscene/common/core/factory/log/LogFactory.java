package com.suchness.realscene.common.core.factory.log;


import com.suchness.realscene.common.bean.constant.state.LogSucceed;
import com.suchness.realscene.common.bean.constant.state.LogType;
import com.suchness.realscene.common.entity.system.LoginLog;
import com.suchness.realscene.common.entity.system.OperationLog;

import java.util.Date;

/**
 * @description 日志对象创建工厂
 * @author hejunfeng
 * @time 2020/7/10 0010 
 */
public class LogFactory {

   /**
    * @description 创建操作日志
    * @author hejunfeng
    * @time 2020/7/10 0010 
    */
    public static OperationLog createOperationLog(LogType logType, Long userId, String bussinessName, String clazzName, String methodName, String msg, LogSucceed succeed) {
        OperationLog operationLog = new OperationLog();
        operationLog.setLogtype(logType.getMessage());
        operationLog.setLogname(bussinessName);
        operationLog.setUserid(userId.intValue());
        operationLog.setClassname(clazzName);
        operationLog.setMethod(methodName);
        operationLog.setCreateTime(new Date());
        operationLog.setSucceed(succeed.getMessage());
        operationLog.setMessage(msg);
        return operationLog;
    }

   /**
    * @description 创建登录日志
    * @author hejunfeng
    * @time 2020/7/10 0010 
    */
    public static LoginLog createLoginLog(LogType logType, Long userId, String msg, String ip) {
        LoginLog loginLog = new LoginLog();
        loginLog.setLogname(logType.getMessage());
        loginLog.setUserid(userId.intValue());
        loginLog.setCreateTime(new Date());
        loginLog.setSucceed(LogSucceed.SUCCESS.getMessage());
        loginLog.setIp(ip);
        loginLog.setMessage(msg);
        return loginLog;
    }
}
