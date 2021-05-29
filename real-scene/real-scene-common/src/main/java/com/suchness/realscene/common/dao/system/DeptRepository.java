package com.suchness.realscene.common.dao.system;

import com.suchness.realscene.common.dao.BaseRepository;
import com.suchness.realscene.common.entity.system.Dept;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author: rs
 * @date: 2020/6/30 9:39
 * @description:
 * 部门仓库类
 */
public interface DeptRepository extends BaseRepository<Dept, Long> {
    List<Dept> findByPidsLike(String pid);
    @Query(nativeQuery = true,value = "SELECT id, pid AS pId, simplename AS NAME, ( CASE WHEN (pId = 0 OR pId IS NULL) THEN 'true' ELSE 'false' END ) AS open FROM t_sys_dept")
    List tree();

    List<Dept> findBySimplenameLikeOrFullnameLike(String name, String name2);

    /**
     * 部门名称或全称不能重复
     * @param simplename
     * @param fullname
     * @return
     */
    @Query(nativeQuery = true,value = "select * from t_sys_dept where simplename like %?1% or fullname like %?2%")
    Dept getDeptByName(String simplename,String fullname);
}
