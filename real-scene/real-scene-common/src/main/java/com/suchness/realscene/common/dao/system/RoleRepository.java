package com.suchness.realscene.common.dao.system;

import com.suchness.realscene.common.dao.BaseRepository;
import com.suchness.realscene.common.entity.system.Role;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/***
 *  author: wch
 *  create_time : 2020/6/16 9:10
 *******/
public interface RoleRepository  extends BaseRepository<Role,Long> {

    List findByName(String roleName);

    @Query(nativeQuery = true,value = "SELECT id, pId, `name`, ( CASE WHEN (`pId` = 0 OR `pId` IS NULL) THEN 'true' ELSE 'false' END ) `open` FROM t_sys_role")
    List roleTreeList();

    @Query(nativeQuery = true,value="SELECT r.id AS id, pId AS pId, NAME AS NAME, ( CASE WHEN (pId = 0 OR pId IS NULL) THEN 'true' ELSE 'false' END ) \"open\", ( CASE WHEN (r1.ID = 0 OR r1.ID IS NULL) THEN 'false' ELSE 'true' END ) AS checked FROM t_sys_role r LEFT JOIN ( SELECT ID FROM t_sys_role WHERE ID IN (?1)) r1 ON r.ID = r1.ID ORDER BY pId, num ASC")
    List roleTreeListByRoleId(Long[] ids);
}
