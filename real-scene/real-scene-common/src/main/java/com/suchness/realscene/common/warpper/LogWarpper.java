package com.suchness.realscene.common.warpper;

import com.suchness.realscene.common.Constants;
import com.suchness.realscene.common.service.system.impl.ConstantFactory;
import com.suchness.realscene.common.utils.DateUtils;
import com.suchness.realscene.common.utils.StringUtil;

import java.util.Date;
import java.util.Map;

/**
 * 日志列表的包装类
 *
 * @author wangchan
 * @date 2020 7/7
 */
public class LogWarpper extends BaseControllerWarpper {

    public LogWarpper(Object list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        String message = (String) map.get("message");

        Long userid = Long.valueOf(map.get("userid").toString());
        map.put("userName", ConstantFactory.me().getUserNameById(userid));

        //如果信息过长,则只截取前100位字符串
        if (StringUtil.isNotEmpty(message) && message.length() >= 100) {
            String subMessage = message.substring(0, 100) + "...";
            map.put("message", subMessage);
        }
        Date createTime = (Date)map.get("createTime");
        map.put("createtime", DateUtils.format((Date) map.get("createTime"),"yyyy-MM-dd HH:mm:ss"));
        //如果信息中包含分割符号;;;   则分割字符串返给前台
        if (StringUtil.isNotEmpty(message) && message.indexOf(Constants.SEPARATOR) != -1) {
            String[] msgs = message.split(Constants.SEPARATOR);
            map.put("regularMessage",msgs);
        }else{
            map.put("regularMessage",message);
        }
    }

}
