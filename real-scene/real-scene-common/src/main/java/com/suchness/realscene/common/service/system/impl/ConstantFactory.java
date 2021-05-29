package com.suchness.realscene.common.service.system.impl;

import com.suchness.realscene.common.SpringContextHolder;
import com.suchness.realscene.common.bean.constant.cache.CacheKey;
import com.suchness.realscene.common.bean.constant.state.ManagerStatus;
import com.suchness.realscene.common.cache.DictCache;
import com.suchness.realscene.common.dao.system.DeptRepository;
import com.suchness.realscene.common.dao.system.DictRepository;
import com.suchness.realscene.common.dao.system.RoleRepository;
import com.suchness.realscene.common.dao.system.UserRepository;
import com.suchness.realscene.common.entity.system.Dept;
import com.suchness.realscene.common.entity.system.Dict;
import com.suchness.realscene.common.entity.system.Role;
import com.suchness.realscene.common.entity.system.User;
import com.suchness.realscene.common.service.system.IConstantFactory;
import com.suchness.realscene.common.utils.Convert;
import com.suchness.realscene.common.utils.StringUtil;
import com.suchness.realscene.common.utils.ToolUtil;
import com.suchness.realscene.common.utils.cache.TimeCacheMap;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;


/**
 * @author: rs
 * @date: 2020/6/20 8:43
 * @description:
 * 常量的生产工厂
 */
@Component
@DependsOn("springContextHolder")
@CacheConfig
public class ConstantFactory implements IConstantFactory {
    public static TimeCacheMap<String, String> cache = new TimeCacheMap<String, String>(3600, 2);

    private DictCache dictCache = SpringContextHolder.getBean(DictCache.class);
    private DictRepository dictRepository = SpringContextHolder.getBean(DictRepository.class);
    private UserRepository userRepository = SpringContextHolder.getBean(UserRepository.class);
    private DeptRepository deptRepository = SpringContextHolder.getBean(DeptRepository.class);
    private RoleRepository roleRepository = SpringContextHolder.getBean(RoleRepository.class);

    public static IConstantFactory me() {
        return SpringContextHolder.getBean("constantFactory");
    }

    public String get(String key) {
        return cache.get(key);
    }

    public void set(String key, String val) {
        cache.put(key, val);
    }

    /**
     * 根据用户id获取用户名称
     *
     * @author stylefeng
     * @Date 2017/5/9 23:41
     */
    @Override
    public String getUserNameById(Long userId) {
        String val = get(CacheKey.SYS_USER_NAME + userId);
        if (StringUtil.isNotEmpty(val)) {
            return val;
        }

        User user = getUser(userId);
        if (user != null) {
            val = user.getName();
            set(CacheKey.SYS_USER_NAME + userId, val);
            return val;
        }


        return "--";
    }

    private User getUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user;
        }
        return null;
    }

    /**
     * 根据用户id获取用户账号
     *
     * @author stylefeng
     * @date 2017年5月16日21:55:371
     */
    @Override
    public String getUserAccountById(Long userId) {
        User user = getUser(userId);
        if (user != null) {
            return user.getAccount();
        } else {
            return "--";
        }
    }

    /**
     * 获取部门名称
     */
    @Override
    public String getDeptName(Long deptId) {
       if (deptId == null) {
            return null;
        }
        String val = get(CacheKey.DEPT_NAME + deptId);
        if (StringUtil.isNotEmpty(val)) {
            return val;
        }
        Dept dept = getDept(deptId);
        if (StringUtil.isNotNullOrEmpty(dept) && StringUtil.isNotEmpty(dept.getFullname())) {
            val = dept.getFullname();
            set(CacheKey.DEPT_NAME + deptId, val);
            return val;
        }
        return "";
    }

    @Override
    public Dept getDept(Long id) {
        Optional<Dept> optional = deptRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    /**
     * 通过角色id获取角色名称
     */
    @Override
    public String getSingleRoleName(Long roleId) {
        if (roleId == null || 0 == roleId  ) {
            return "--";
        }
        Role roleObj = getRole(roleId);
        if (StringUtil.isNotNullOrEmpty(roleObj) && StringUtil.isNotEmpty(roleObj.getName())) {
            return roleObj.getName();
        }
        return "";
    }

    @Override
    public Role getRole(Long id) {
        Optional<Role> optional = roleRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    /**
     * 获取性别名称
     */
    @Override
    public String getSexName(Integer sex) {
        return getDictsByName("性别", String.valueOf(sex));
    }

    /**
     * 查询字典
     */
    @Override
    public List<Dict> findInDict(Long id) {
        return dictRepository.findByPid(id);
    }

    @Override
    public void cleanLocalCache() {
        cache.neverCleanup();
    }


    /**
     * 根据字典名称和字典中的值获取对应的名称
     */
    @Override
    public String getDictsByName(String name, String val) {
        String result = get(CacheKey.DICT_NAME + name + val);
        if (StringUtil.isNotEmpty(result)) {
            return result;
        }
        List<Dict> dicts = dictCache.getDictsByPname(name);
        if(ToolUtil.isNotEmpty(dicts)){
            for (Dict item : dicts) {
                if (item.getNum() != null && item.getNum().equals(val)) {
                    result = item.getName();
                    set(CacheKey.DICT_NAME + name + val, result);
                    return result;
                }
            }
        }

        return "";

    }

    /**
     * 通过角色ids获取角色名称
     */
    @Override
    public String getRoleName(String roleIds) {
        String val = get(CacheKey.ROLES_NAME + roleIds);
        if (StringUtil.isNotEmpty(val)) {
            return val;
        }
        Integer[] roles = Convert.toIntArray(roleIds);
        StringBuilder sb = new StringBuilder();
        for (Integer role : roles) {
            Role roleObj = getRole(Long.valueOf(role));
            if (StringUtil.isNotNullOrEmpty(roleObj) && StringUtil.isNotEmpty(roleObj.getName())) {
                sb.append(roleObj.getName()).append(",");
            }
        }
        val = StringUtil.removeSuffix(sb.toString(), ",");
        set(CacheKey.ROLES_NAME + roleIds, val);
        return val;
    }


    /**
     * 获取用户登录状态
     */
    @Override
    public String getStatusName(Integer status) {
        return ManagerStatus.valueOf(status);
    }


}
