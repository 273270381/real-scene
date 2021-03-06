package com.suchness.realscene.common.security;

import com.suchness.realscene.common.SpringContextHolder;
import com.suchness.realscene.common.bean.ShiroUser;
import com.suchness.realscene.common.cache.TokenCache;
import com.suchness.realscene.common.dao.system.MenuRepository;
import com.suchness.realscene.common.dao.system.RoleRepository;
import com.suchness.realscene.common.entity.system.Role;
import com.suchness.realscene.common.entity.system.User;
import com.suchness.realscene.common.utils.Convert;
import com.suchness.realscene.common.utils.HttpUtil;
import com.suchness.realscene.common.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 *  author: wch
 *  create_time : 2020/6/18 17:07
 *******/
@Service
@DependsOn("springContextHolder")
@Transactional(readOnly = true)
public class ShiroFactroy {

    @Autowired
    private TokenCache tokenCache;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private RoleRepository roleRepository;


    public static ShiroFactroy me(){
        return SpringContextHolder.getBean(ShiroFactroy.class);
    }

    public ShiroUser shiroUser(User user){
        ShiroUser shiroUser = tokenCache.getUser(HttpUtil.getToken());
        if(shiroUser != null){
            return shiroUser;
        }

        shiroUser = new ShiroUser();
        shiroUser.setId(Long.valueOf(user.getId()));            // 账号id
        shiroUser.setAccount(user.getAccount());// 账号
        shiroUser.setDeptId(user.getDeptid());    // 部门id
        //shiroUser.setDeptName(ConstantFactory.me().getDeptName(user.getDeptid()));// 部门名称
        shiroUser.setName(user.getName());        // 用户名称
        shiroUser.setPassword(user.getPassword());
        Long[] roleArray = Convert.toLongArray(",", user.getRoleid());
        List<Long> roleList = new ArrayList<Long>();
        List<String> roleNameList = new ArrayList<String>();
        List<String> roleCodeList = new ArrayList<String>();
        Set<String> permissions = new HashSet<String>();
        Set<String> resUrls = new HashSet<>();
        for (Long roleId : roleArray) {
            roleList.add(roleId);
            Role role = roleRepository.getOne(roleId);
            roleNameList.add(role.getName());
            roleCodeList.add(role.getTips());
            permissions.addAll(menuRepository.getResCodesByRoleId(roleId));
            List<String> list = menuRepository.getResUrlsByRoleId(roleId);
            for(String resUrl:list) {
                if(StringUtil.isNotEmpty(resUrl)) {
                    resUrls.add(resUrl);
                }
            }


        }

        shiroUser.setRoleList(roleList);
        shiroUser.setRoleNames(roleNameList);
        shiroUser.setRoleCodes(roleCodeList);
        shiroUser.setPermissions(permissions);

        shiroUser.setUrls(resUrls);
        tokenCache.setUser(HttpUtil.getToken(),shiroUser);
        return shiroUser;
    }

}
