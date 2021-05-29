package com.suchness.realscene.common.cache;


import com.suchness.realscene.common.entity.system.Dict;

import java.util.List;

/**
 * @author: rs
 * @date: 2020/7/2 8:50
 * @description:
 * 字典缓存服务
 */
public interface DictCache extends Cache{

    /**
     * 根据名称查找字典
     * @param dictName
     * @return
     */
    List<Dict> getDictsByPname(String dictName);

    /**
     * 根据ID获取
     * @param dictId
     * @return
     */
    String getDict(Long dictId);
}
