package com.suchness.realscene.api.controller.system;

import com.suchness.realscene.api.constants.ApiConstants;
import com.suchness.realscene.api.controller.BaseController;
import com.suchness.realscene.common.bean.ShiroUser;
import com.suchness.realscene.common.cache.CacheDao;
import com.suchness.realscene.common.cache.TokenCache;
import com.suchness.realscene.common.core.factory.log.LogManager;
import com.suchness.realscene.common.core.factory.log.LogTaskFactory;
import com.suchness.realscene.common.entity.system.User;
import com.suchness.realscene.common.enums.Permission;
import com.suchness.realscene.common.security.JwtUtil;
import com.suchness.realscene.common.security.ShiroFactroy;
import com.suchness.realscene.common.service.system.UserService;
import com.suchness.realscene.common.utils.*;
import com.suchness.realscene.common.vo.Rets;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.nutz.mapl.Mapl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/***
 *  author: wch
 *  create_time : 2020/6/12 15:47
 *  登录controller
 *******/
@RestController
@RequestMapping("/account")
public class AccountController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CacheDao cacheDao;
    @Autowired
    private TokenCache tokenCache;

    /**
     * 用户登录<br>
     * 1，验证没有注册<br>
     * 2，验证密码错误<br>
     * 3，登录成功
     *
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestParam("username") String userName,
                        @RequestParam("password") String password,
                        @RequestParam(value = "letOut", required = false) Boolean letOut) {
        try {
        /*    //验证证书
            LicenseVerify licenseVerify = new LicenseVerify();
            //校验证书是否有效
            boolean verifyResult = licenseVerify.verify();
            if (!verifyResult) {
                return Rets.failureLogin("您的证书无效，请核查服务器是否取得授权或重新申请证书！");
            }*/

            //1,
            User user = userService.findByAccount(userName);
            if (user == null) {
                return Rets.failureLogin("用户名或密码错误");
            }
            String passwdMd5 = MD5.md5(password, user.getSalt());

            //处理session
            SessionsSecurityManager securityManager = (SessionsSecurityManager) SecurityUtils.getSecurityManager();
            DefaultSessionManager sessionManager = (DefaultSessionManager) securityManager.getSessionManager();
           /* DefaultWebSecurityManager sessionManagerq = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
            Authenticator authenticator = sessionManagerq.getAuthenticator();
            System.out.println(authenticator);*/
            Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();//获取当前已登录的用户session列表
            for (Session session : sessions) {
                SimplePrincipalCollection attribute = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                String s = "";
                if (attribute != null) {
                    s = attribute.toString();
                }
                String usernameSession = JwtUtil.getUsername(s);
                //如果和当前session是同一个session，则不剔除
                if (SecurityUtils.getSubject().getSession().getId().equals(session.getId())) {
                    break;
                }
                if (usernameSession != null && attribute != null) {
                    if (usernameSession.equals(userName) & user.getPassword().equals(passwdMd5)) {
                        if (ToolUtil.isNotEmpty(letOut) && letOut) {
                            System.out.println(userName + "-----" + usernameSession + "------已登录，剔除中...");
                            //   Object principal = SecurityUtils.getSubject().getPrincipal();
                            sessionManager.getSessionDAO().delete(session);
                            // SecurityUtils.getSubject().logout();
                            // return Rets.failure("已异地登录");
                        } else {
                            Map<String, String> map = new HashMap<>();
                            map.put("username", userName);
                            map.put("password", password);

                            return Rets.isLogined("", map);

                        }


                    }

                }
            }


            //2,
            if (!user.getPassword().equals(passwdMd5)) {
                return Rets.failureLogin("用户名或密码错误");
            }
/*            if(StringUtil.isEmpty(user.getRoleid())){
                return Rets.failure("该用户未配置权限");
            }*/
            String token = userService.loginForToken(user);
            Map<String, String> result = new HashMap<>(1);
            result.put("token", token);
            LogManager.me().executeLog(LogTaskFactory.loginLog(user.getId(), HttpUtil.getIp()));
            return Rets.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Rets.failure("登录时失败");
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Object info() {
        HttpServletRequest request = HttpUtil.getRequest();
        Long idUser;
        try {
            idUser = getIdUser(request);
        } catch (Exception e) {
            return Rets.expire();
        }
        if (idUser != null) {
            User user = userService.get(idUser);
            if (StringUtil.isEmpty(user.getRoleid())) {
                return Rets.failure("该用户未配置权限");
            }
            ShiroUser shiroUser = ShiroFactroy.me().shiroUser(user);
            Map map = Maps.newHashMap("name", user.getName(), "role", "admin", "roles", shiroUser.getRoleCodes());
            map.put("permissions", shiroUser.getUrls());
            Map profile = (Map) Mapl.toMaplist(user);
            profile.put("dept", shiroUser.getDeptName());
            profile.put("roles", shiroUser.getRoleNames());
            map.put("profile", profile);

            return Rets.success(map);
        }
        return Rets.failure("获取用户信息失败");
    }

    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    public Object updatePwd(String oldPassword, String password, String rePassword) {
        try {

            if (StringUtil.isEmpty(password) || StringUtil.isEmpty(rePassword)) {
                return Rets.failure("密码不能为空");
            }
            if (!password.equals(rePassword)) {
                return Rets.failure("新密码前后不一致");
            }
            User user = userService.get(getIdUser(HttpUtil.getRequest()));
            if (ApiConstants.ADMIN_ACCOUNT.equals(user.getAccount())) {
                return Rets.failure("不能修改超级管理员密码");
            }
            if (!MD5.md5(oldPassword, user.getSalt()).equals(user.getPassword())) {
                return Rets.failure("旧密码输入错误");
            }

            user.setPassword(MD5.md5(password, user.getSalt()));
            userService.update(user);
            return Rets.success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Rets.failure("更改密码失败");
    }

    /**
     * 重置密码
     *
     * @param id         用户ID
     * @param password
     * @param rePassword
     * @return
     */
    @RequiresPermissions(value = {Permission.USER_RESET_PASSWORD})
    @RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
    public Object resetPwd(Long id, String password, String rePassword) {
        try {
            if (ToolUtil.isEmpty(id)) {
                return Rets.failure("用户不能为空，请重试");
            }
            if (StringUtil.isEmpty(password) || StringUtil.isEmpty(rePassword)) {
                return Rets.failure("密码不能为空");
            }
            if (!password.equals(rePassword)) {
                return Rets.failure("新密码前后不一致");
            }
            User user = userService.get(id);
//            if(ApiConstants.ADMIN_ACCOUNT.equals(user.getAccount())){
//                return Rets.failure("不能修改超级管理员密码");
//            }

            user.setPassword(MD5.md5(password, user.getSalt()));
            userService.update(user);
            return Rets.success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Rets.failure("更改密码失败");
    }

}
