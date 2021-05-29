package com.suchness.realscene.common.warpper;


import com.suchness.realscene.common.entity.system.Dict;
import com.suchness.realscene.common.service.system.impl.ConstantFactory;
import com.suchness.realscene.common.utils.StringUtil;

import java.util.List;
import java.util.Map;

/**
 * @author: rs
 * @date: 2020/7/2 8:56
 * @description:
 * 字典列表的包装
 */
public class DictWarpper extends BaseControllerWarpper {

    public DictWarpper(Object list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        StringBuffer detail = new StringBuffer();
        Long id = (Long) map.get("id");
        List<Dict> dicts = ConstantFactory.me().findInDict(id);
        if(dicts != null){
            for (Dict dict : dicts) {
                detail.append(dict.getNum() + ":" +dict.getName() + ",");
            }
            map.put("detail", StringUtil.removeSuffix(detail.toString(),","));
        }
    }

}
