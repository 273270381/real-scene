package com.suchness.realscene.api.controller.system;


import com.suchness.realscene.api.controller.BaseController;
import com.suchness.realscene.common.SearchFilter;
import com.suchness.realscene.common.bean.constant.factory.PageFactory;
import com.suchness.realscene.common.bean.constant.state.BizLogType;
import com.suchness.realscene.common.entity.system.OperationLog;
import com.suchness.realscene.common.enums.Permission;
import com.suchness.realscene.common.service.system.OperationLogService;
import com.suchness.realscene.common.utils.BeanUtil;
import com.suchness.realscene.common.utils.HttpUtil;
import com.suchness.realscene.common.utils.StringUtil;
import com.suchness.realscene.common.utils.factory.Page;
import com.suchness.realscene.common.vo.Rets;
import com.suchness.realscene.common.utils.DateUtils;
import com.suchness.realscene.common.warpper.LogWarpper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * LogController
 *
 * @author wangchan
 * @version 2020/07/06
 */
@RestController
@RequestMapping("/log")
public class LogController extends BaseController {
    @Autowired
    OperationLogService operationLogService;


    /**
     * 查询操作日志列表
     *
     * @Param [beginTime, endTime, logName, logType]
     * @Return java.lang.Object
     **/
    @RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions(value = {Permission.LOG})
    public Object list(@RequestParam(required = false) String beginTime,
                       @RequestParam(required = false) String endTime,
                       @RequestParam(required = false) String logName,
                       @RequestParam(required = false) Integer logType) {
        Page<OperationLog> page = new PageFactory<OperationLog>().defaultPage();
        if (StringUtil.isNotEmpty(beginTime)) {
            page.addFilter("createTime", SearchFilter.Operator.GTE, DateUtils.parseDate(beginTime));
        }
        if (StringUtil.isNotEmpty(endTime)) {
            page.addFilter("createTime", SearchFilter.Operator.LTE, DateUtils.parseDate(endTime));
        }
        page.addFilter("logname", SearchFilter.Operator.LIKE, logName);
        if (logType != null) {
            page.addFilter(SearchFilter.build("logtype", SearchFilter.Operator.EQ, BizLogType.valueOf(logType)));
        }
        page = operationLogService.queryPage(page);
        page.setRecords((List<OperationLog>) new LogWarpper(BeanUtil.objectsToMaps(page.getRecords())).warp());
        return Rets.success(page);
    }

    /**
     * 查询指定用户的操作日志列表
     *
     * @Date 16:07 2020/8/28
     * @Param []
     * @Return java.lang.Object
     **/
    @RequestMapping("/queryByUser")
    @ResponseBody
    public Object list() {
        Page<OperationLog> page = new Page<OperationLog>();
        page.addFilter(SearchFilter.build("userid", SearchFilter.Operator.EQ, getIdUser(HttpUtil.getRequest())));
        Page<OperationLog> pageResult = operationLogService.queryPage(page);
        pageResult.setRecords((List<OperationLog>) new LogWarpper(BeanUtil.objectsToMaps(page.getRecords())).warp());
        return Rets.success(pageResult.getRecords());
    }

    /**
     * 清空日志
     *
     * @Date 16:06 2020/8/28
     * @Param []
     * @Return java.lang.Object
     **/
    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    //  @RequiresPermissions(value = {Permission.LOG_CLEAR})
    public Object delLog() {
        operationLogService.clear();
        return Rets.success();
    }

}
