package com.suchness.realscene.common.cache.impl;

import com.suchness.realscene.common.bean.constant.cache.CacheKey;
import com.suchness.realscene.common.cache.BaseCache;
import com.suchness.realscene.common.cache.CacheDao;
import com.suchness.realscene.common.cache.DictCache;
import com.suchness.realscene.common.dao.system.DictRepository;
import com.suchness.realscene.common.entity.system.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: rs
 * @date: 2020/7/2 9:06
 * @description:
 * 字典缓存服务继承实现类
 */
@Component
public class DictCacheImpl extends BaseCache implements DictCache {

    @Autowired
    private DictRepository dictRepository;

    @Autowired
    private CacheDao cacheDao;

    @Override
    public List<Dict> getDictsByPname(String dictName) {
        return (List<Dict>) cacheDao.hget(CacheDao.CONSTANT, CacheKey.DICT + dictName, List.class);
    }

    @Override
    public String getDict(Long dictId) {
        return (String) get(CacheKey.DICT_NAME +dictId);
    }

    @Override
    public void cache() {
        super.cache();
        List<Dict> list = dictRepository.findByPid(0L);
        for (Dict dict : list) {
            List<Dict> children = dictRepository.findByPid(dict.getId());
            if (children.isEmpty()) {
                continue;
            }
            set(String.valueOf(dict.getId()), children);
            set(dict.getName(), children);
            for (Dict child : children) {
                set(CacheKey.DICT_NAME + child.getId(), child.getName());
            }
        }
    }

    @Override
    public Object get(String key) {
        return cacheDao.hget(CacheDao.CONSTANT, CacheKey.DICT + key);
    }

    @Override
    public void set(String key, Object val) {
        cacheDao.hset(CacheDao.CONSTANT, CacheKey.DICT + key, val);
    }
}
