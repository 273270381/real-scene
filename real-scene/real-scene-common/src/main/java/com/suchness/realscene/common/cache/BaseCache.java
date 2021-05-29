package com.suchness.realscene.common.cache;

import com.suchness.realscene.common.SpringContextHolder;
import com.suchness.realscene.common.service.system.impl.ConstantFactory;

/**
 * @author: rs
 * @date: 2020/7/2 9:06
 * @description:
 * 基础缓存
 */
public abstract  class BaseCache implements Cache {

    @Override
    public void cache() {
        SpringContextHolder.getBean(ConstantFactory.class).cleanLocalCache();
    }
}
