
package com.suchness.realscene.common.dao.system;


import com.suchness.realscene.common.dao.BaseRepository;
import com.suchness.realscene.common.entity.system.Dict;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: rs
 * @date: 2020/7/2 8:46
 * @description:
 * 字典仓库类
 */
public interface DictRepository extends BaseRepository<Dict,Long> {

    /**
     * 根据ID查找信息
     * @param pid
     * @return
     */
    List<Dict> findByPid(Long pid);

    /**
     * 根据名称和父ID查找记录
     * @param name
     * @param pid
     * @return
     */
    List<Dict> findByNameAndPid(String name, Long pid);

    /**
     * 根据名称匹配记录
     * @param name
     * @return
     */
    List<Dict> findByNameLike(String name);


    @Query(value = "select name from t_sys_dict where id=?1",nativeQuery = true)
    String queryTypeById(@Param("name") int type);
}
