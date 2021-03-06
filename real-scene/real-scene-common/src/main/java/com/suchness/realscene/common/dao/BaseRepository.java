package com.suchness.realscene.common.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/***
 *  author: wch
 *  create_time : 2020/6/12 14:13
 *******/
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>
        , PagingAndSortingRepository<T, ID>
        , JpaSpecificationExecutor<T> {

    List<Map> queryBySql(String sql);

    /**
     * 根据原生sql查询数组对象
     * @param sql
     * @return
     */
    Map getMapBySql(String sql);

    /**
     * 根据原生sql查询对象列表
     * @param sql
     * @param klass
     * @return
     */
    List<T> queryBySql(String sql,Class<T> klass);
    List<?> queryObjBySql(String sql,Class<?> klass);


    /**
     * 根据原生sql查询对象列表
     * @param sql
     * @return
     */
    List<T> query(String sql);

    T getBySql(String sql);

    /**
     * 根据原生sql查询对象
     * @param sql
     * @return
     */
    T get(String sql);
    T getOne(ID id);

    /**
     * 执行sql
     * @param sql
     * @return
     */
    int execute(String sql);

    /**
     * 获取数据类型
     * @return
     */
    Class<T> getDataClass();
}
