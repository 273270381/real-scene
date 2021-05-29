package com.suchness.realscene.common.warpper;

import com.suchness.realscene.common.service.system.impl.ConstantFactory;
import com.suchness.realscene.common.utils.StringUtil;

import java.util.List;
import java.util.Map;

/**
 * @author: rs
 * @date: 2020/6/27 9:21
 * @description:
 * 用户管理的包装类
 */
public class UserWarpper extends BaseControllerWarpper {

    public UserWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        map.put("sexName", ConstantFactory.me().getSexName((Integer) map.get("sex")));
        if(StringUtil.isNotNullOrEmpty(map.get("roleid"))) {
            map.put("roleName", ConstantFactory.me().getRoleName((String) map.get("roleid")));
        }
        if(StringUtil.isNotNullOrEmpty(map.get("deptid"))) {
            map.put("deptName", ConstantFactory.me().getDeptName((Long) map.get("deptid")));
        }
        map.put("statusName", ConstantFactory.me().getStatusName((Integer) map.get("status")));

    }

}
