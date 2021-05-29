package com.suchness.realscene.api.controller.system;

import com.suchness.realscene.api.controller.BaseController;
import com.suchness.realscene.common.SearchFilter;
import com.suchness.realscene.common.bean.constant.factory.PageFactory;
import com.suchness.realscene.common.entity.system.LoginLog;
import com.suchness.realscene.common.enums.Permission;
import com.suchness.realscene.common.service.system.LoginLogService;
import com.suchness.realscene.common.utils.BeanUtil;
import com.suchness.realscene.common.utils.DateUtils;
import com.suchness.realscene.common.utils.StringUtil;
import com.suchness.realscene.common.utils.factory.Page;
import com.suchness.realscene.common.vo.Rets;
import com.suchness.realscene.common.warpper.LogWarpper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;



/**
 * @Author hejunfeng
 * @Date 13:39 2020/8/28 0028
 * @Param
 * @return
 * @Description 登陆日志controller
 **/
@RestController
@RequestMapping("/loginLog")
public class LoginLogController extends BaseController {
    @Autowired
    LoginLogService loginLogService;


    /**
     * @Author hejunfeng
     * @Date 13:39 2020/8/28 0028
     * @Param [beginTime, endTime]
     * @return java.lang.Object
     * @Description 查询登陆日志列表
     **/
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {Permission.LOGIN_LOG})
    public Object list(@RequestParam(required = false) String beginTime,
                       @RequestParam(required = false) String endTime){
        Page<LoginLog> p = new PageFactory<LoginLog>().defaultPage();
        if(StringUtil.isNotEmpty(beginTime)) {
            p.addFilter("createTime", SearchFilter.Operator.GTE, DateUtils.parseDate(beginTime));
        }
        if(StringUtil.isNotEmpty(endTime)) {
            p.addFilter("createTime", SearchFilter.Operator.LTE, DateUtils.parseDate(endTime));
        }
        p = loginLogService.queryPage(p);
        p.setRecords((List<LoginLog>) new LogWarpper(BeanUtil.objectsToMaps(p.getRecords())).warp());
        return Rets.success(p);
    }



    /**
     * @Author hejunfeng
     * @Date 13:40 2020/8/28 0028
     * @Param []
     * @return java.lang.Object
     * @Description 清空登陆日志
     **/
    @RequestMapping(method = RequestMethod.DELETE)
    @RequiresPermissions(value = {Permission.LOGIN_LOG_CLEAR})
    public Object clear() {
        loginLogService.clear();
        return Rets.success();
    }



    /**
     * @Author hejunfeng
     * @Date 13:40 2020/8/28 0028
     * @Param [userid]
     * @return java.lang.Object
     * @Description 查询最后一条登陆日志
     **/
    @RequestMapping(value = "/getLogInfo", method = RequestMethod.POST)
    public Object getLogInfo(@RequestParam(required = false) Long userid){
        Map map =  loginLogService.queryLog(userid);
        return Rets.success(map);
    }
}
