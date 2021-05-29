package com.suchness.realscene.common.bean.dictmap;


import com.suchness.realscene.common.bean.dictmap.base.AbstractDictMap;

/**
 * @author: rs
 * @date: 2020/6/30 9:48
 * @description:
 * 部门的映射
 */
public class DeptDict extends AbstractDictMap {

    @Override
    public void init() {
        put("deptId", "部门名称");
        put("id","部门名称");
        put("num", "部门排序");
        put("pid", "上级名称");
        put("simplename", "部门简称");
        put("fullname", "部门全称");
        put("tips", "备注");
    }

    @Override
    protected void initBeWrapped() {
        putFieldWrapperMethodName("deptId", "getDeptName");
        putFieldWrapperMethodName("pid", "getDeptName");
    }
}
