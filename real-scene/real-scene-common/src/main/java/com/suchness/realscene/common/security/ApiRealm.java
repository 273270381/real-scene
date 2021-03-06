package com.suchness.realscene.common.security;

import com.suchness.realscene.common.bean.ShiroUser;
import com.suchness.realscene.common.entity.system.User;
import com.suchness.realscene.common.service.system.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Set;

/***
 *  author: wch
 *  create_time : 2020/6/19 10:27
 *******/
@Service
public class ApiRealm extends AuthorizingRealm {
    private Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private UserService userService;
    @Autowired
    private ShiroFactroy shiroFactroy;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = JwtUtil.getUsername(principals.toString());

        ShiroUser user = shiroFactroy.shiroUser(userService.findByAccount(username));
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(user.getRoleCodes());
        Set<String> permission = user.getPermissions();
        simpleAuthorizationInfo.addStringPermissions(permission);
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JwtUtil.getUsername(token);
      //  System.out.println("UserName " +username );


        if (username == null) {
            throw new AuthenticationException("token invalid");
        }

        ShiroUser userBean =  ShiroFactroy.me().shiroUser(userService.findByAccount(username));
        if (userBean == null) {
            throw new AuthenticationException("User didn't existed!");
        }
        try {
            if (!JwtUtil.verify(token, username, userBean.getPassword())) {
                throw new AuthenticationException("Username or password error");
            }
        }catch (Exception e){
            throw  new AuthenticationException(e.getMessage());
        }

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
