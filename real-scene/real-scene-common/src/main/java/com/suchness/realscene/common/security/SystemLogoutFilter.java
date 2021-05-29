package com.suchness.realscene.common.security;

import com.alibaba.fastjson.JSON;
import com.suchness.realscene.common.utils.StringUtil;
import com.suchness.realscene.common.vo.Rets;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Enumeration;

/***
 *  author: wch
 *  create_time : 2020/6/19 10:43
 *******/
public class SystemLogoutFilter extends LogoutFilter {

    private static final Logger logger = LoggerFactory.getLogger(SystemLogoutFilter.class);

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        Subject currentUser = org.apache.shiro.SecurityUtils.getSubject();
        String currentToken="";
        if(currentUser!= null || currentUser.getPrincipal() != null) {

        }
            if(currentUser.getPrincipal()!=null){
                currentToken = currentUser.getPrincipal().toString();
            }



        try {
            /*SessionsSecurityManager securityManager = (SessionsSecurityManager) SecurityUtils.getSecurityManager();
            DefaultSessionManager sessionManager = (DefaultSessionManager) securityManager.getSessionManager();
            Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();
            for (Session session : sessions) {
                SimplePrincipalCollection attribute = (SimplePrincipalCollection)session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                String s="";
                if(attribute!=null){
                    s=attribute.toString();
                }
                if(currentToken.equals(s)){

                    sessionManager.getSessionDAO().delete(session);
                }
            } */
            subject.logout();

        } catch (Exception ex) {
            logger.error("退出登录错误",ex);
        }

        this.writeResult(response);
        //不执行后续的过滤器
        return false;
    }

    private void writeResult(ServletResponse response){
        //响应Json结果
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(JSON.toJSONString(Rets.success()));
        } catch (IOException e) {
            logger.error("返回Response信息出现IOException异常:" + e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
