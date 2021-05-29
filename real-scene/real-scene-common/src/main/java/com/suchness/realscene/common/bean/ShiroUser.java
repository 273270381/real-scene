package com.suchness.realscene.common.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/***
 *  author: wch
 *  create_time : 2020/6/18 16:57
 *******/
@Data
public class ShiroUser implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓名
     */
    private String name;
    /**
     * 部门id
     */
    private Long deptId;
    /***
     * 角色集
     */
    private List<Long> roleList;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 角色名称集
     */
    private List<String> roleNames;
    /**
     * 角色编码
     */
    private List<String> roleCodes;
    /**
     * 资源路径
     */
    private Set<String> urls;
    /**
     * 资源编码
     */
    private Set<String> permissions;
}
