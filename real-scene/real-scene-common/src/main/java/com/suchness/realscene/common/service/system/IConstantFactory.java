package com.suchness.realscene.common.service.system;


import com.suchness.realscene.common.entity.system.Dept;
import com.suchness.realscene.common.entity.system.Dict;
import com.suchness.realscene.common.entity.system.Role;

import java.util.List;

/**
 * @author: rs
 * @date: 2020/6/19 8:43
 * @description:
 * 常量生产工厂的接口
 */
public interface IConstantFactory {

    /**
     * 根据用户id获取用户名称
     *
     * @author stylefeng
     * @Date 2017/5/9 23:41
     */
    String getUserNameById(Long userId);

    /**
     * 根据用户id获取用户账号
     *
     * @author stylefeng
     * @date 2017年5月16日21:55:371
     */
    String getUserAccountById(Long userId);

    /**
     * 通过角色id获取角色名称
     */
    String getSingleRoleName(Long roleId);

    /**
     * 获取部门名称
     */
    String getDeptName(Long deptId);

    Dept getDept(Long id);

    /**
     * 根据ID获取角色
     * @param id
     * @return
     */
    Role getRole(Long id) ;

    /**
     * 获取性别名称
     */
    String getSexName(Integer sex);

    /**
     * 查询字典
     */
    List<Dict> findInDict(Long id);

    /**
     * 清空缓存
     */
    void cleanLocalCache();

    /**
     * 根据字典名称和字典中的值获取对应的名称
     */
    String getDictsByName(String name, String val);

    /**
     * 通过角色ids获取角色名称
     */
    String getRoleName(String roleIds);


    /**
     * 获取用户登录状态
     */
    String getStatusName(Integer status);

}
