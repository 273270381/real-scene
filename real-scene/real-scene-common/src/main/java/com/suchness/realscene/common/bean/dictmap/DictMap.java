package com.suchness.realscene.common.bean.dictmap;


import com.suchness.realscene.common.bean.dictmap.base.AbstractDictMap;

/**
 * @author: rs
 * @date: 2020/7/2 9:00
 * @description:
 * 字典map
 */
public class DictMap extends AbstractDictMap {

    @Override
    public void init() {
        put("dictId","字典名称");
        put("dictName","字典名称");
        put("dictValues","字典内容");
    }

    @Override
    protected void initBeWrapped() {

    }
}
