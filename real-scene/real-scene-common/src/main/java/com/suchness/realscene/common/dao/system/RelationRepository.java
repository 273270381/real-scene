package com.suchness.realscene.common.dao.system;

import com.suchness.realscene.common.dao.BaseRepository;
import com.suchness.realscene.common.entity.system.Relation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/***
 *  author: wch
 *  create_time : 2020/6/16 10:08
 *******/
public interface RelationRepository extends BaseRepository<Relation,Long> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "delete from t_sys_relation where roleid=?1")
    int deleteByRoleId(Long roleId);
}
