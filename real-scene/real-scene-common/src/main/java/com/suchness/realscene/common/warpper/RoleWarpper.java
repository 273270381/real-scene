package com.suchness.realscene.common.warpper;

import com.suchness.realscene.common.service.system.impl.ConstantFactory;

import java.util.List;
import java.util.Map;

/***
 *  author: wch
 *  create_time : 2020/6/16 9:24
 *******/
public class RoleWarpper extends BaseControllerWarpper{
    public RoleWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        map.put("pName", ConstantFactory.me().getSingleRoleName((Long) map.get("pid")));
        map.put("deptName", ConstantFactory.me().getDeptName((Long) map.get("deptid")));
    }
}
