package com.suchness.realscene.common.service;

/***
 *  author: rushi
 *  create_time : 2020/6/1214:22
 *******/
public interface UpdateService<T, ID> {
    /**
     * 修改记录信息
     *
     * @param record 要修改的对象
     * @return 返回修改的记录
     */
    T update(T record);
}
