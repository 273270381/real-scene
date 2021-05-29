package com.suchness.realscene.common.service;

/***
 *  author: wch
 *  create_time : 2020/6/12 14:18
 *******/
public interface CrudService <T, ID> extends
        InsertService<T, ID>,
        UpdateService<T,ID>,
        DeleteService<ID>,
        SelectService<T, ID>{
}
