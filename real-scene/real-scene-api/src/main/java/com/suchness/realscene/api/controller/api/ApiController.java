package com.suchness.realscene.api.controller.api;

import com.suchness.realscene.api.controller.BaseController;
import com.suchness.realscene.common.SpringContextHolder;
import com.suchness.realscene.common.cache.TokenCache;
import com.suchness.realscene.common.service.system.UserService;
import com.suchness.realscene.common.utils.StringUtil;
import com.suchness.realscene.common.vo.Rets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author: rs
 * @date: 2020/7/16 10:24
 * @description:
 * 接口控制器
 */
@RestController
@RequestMapping("/api")
public class ApiController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TokenCache tokenCache;

    /**
     * 判断是否登陆
     * @param token  权限头部
     * @return
     */
    @PostMapping("isLogin")
    public Object isLogin(@RequestParam("token") String token){
        if(StringUtil.isNullOrEmpty(token)){
            return Rets.failure("参数不可以为空，请重试。");
        }
        UserService userService = SpringContextHolder.getBean(UserService.class);
        if(userService.refreshTokenIsValid(token)) {
            return Rets.success();
        }else{
            return Rets.failure("登陆超时，请重新登陆。");
        }
    }

    /**
     * 退出
     * @param
     */
    @PostMapping("logout")
    public Object logout(String token){
        tokenCache.setUser(token,null);
        return Rets.success();
    }



}
