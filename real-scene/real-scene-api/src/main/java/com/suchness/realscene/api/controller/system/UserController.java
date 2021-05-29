package com.suchness.realscene.api.controller.system;

import com.suchness.realscene.api.controller.BaseController;
import com.suchness.realscene.common.SearchFilter;
import com.suchness.realscene.common.bean.constant.Const;
import com.suchness.realscene.common.bean.constant.state.ManagerStatus;
import com.suchness.realscene.common.bean.core.BussinessLog;
import com.suchness.realscene.common.bean.dictmap.UserDict;
import com.suchness.realscene.common.bean.dto.UserDto;
import com.suchness.realscene.common.bean.constant.factory.PageFactory;
import com.suchness.realscene.common.core.factory.UserFactory;
import com.suchness.realscene.common.entity.system.User;
import com.suchness.realscene.common.enums.BizExceptionEnum;
import com.suchness.realscene.common.enums.Permission;
import com.suchness.realscene.common.exception.ApplicationException;
import com.suchness.realscene.common.service.system.UserService;
import com.suchness.realscene.common.utils.*;
import com.suchness.realscene.common.utils.factory.Page;
import com.suchness.realscene.common.vo.Rets;
import com.suchness.realscene.common.warpper.UserWarpper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author: rs
 * @date: 2020/6/16 8:29
 * @description: 用户控制器
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    /**
     * 用户列表
     * @param account  账户名
     * @param name    姓名
     * @param idDept   部门ID
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.USER})
    public Object list(@RequestParam(required = false) String account,
                       @RequestParam(required = false) String name,
                       @RequestParam(required = false) String idDept) {
        Page page = new PageFactory().defaultPage();
        page.addFilter("name", SearchFilter.Operator.LIKE, name);
        page.addFilter("account", SearchFilter.Operator.LIKE, account);

        page.addFilter("status", SearchFilter.Operator.GT, 0);
        page.addFilter("status", SearchFilter.Operator.LT, 3);

        page.setSort(new Sort(Sort.Order.desc("id")));
        page.setSort(new Sort(Sort.Order.desc("createTime")));

        page = userService.queryPage(page);
        List list = (List) new UserWarpper(BeanUtil.objectsToMaps(page.getRecords())).warp();
        page.setRecords(list);
        return Rets.success(page);
    }

    /**
     * 编辑/保存用户信息
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @BussinessLog(value = "编辑账号", key = "name", dict = UserDict.class)
    @RequiresPermissions(value = {Permission.USER_EDIT})
    public Object save(@Valid UserDto user, BindingResult result) {
        if (user.getId() == null) {
            // 判断账号是否重复
            User theUser = userService.findByAccount(user.getAccount());
            if (theUser != null) {
                throw new ApplicationException(BizExceptionEnum.USER_ALREADY_REG);
            }
            // 完善账号信息
            user.setSalt(RandomUtil.getRandomString(5));
            user.setPassword(MD5.md5(user.getPassword(), user.getSalt()));
            user.setStatus(ManagerStatus.OK.getCode());
            userService.insert(UserFactory.createUser(user, new User()));
        } else {
            User oldUser = userService.get(user.getId());
            userService.update(UserFactory.updateUser(user, oldUser));
        }
        return Rets.success();
    }

    /**
     * 根据用户ID删除记录
     * @param userId  用户ID
     * @return
     */
    @BussinessLog(value = "删除账号", key = "userId", dict = UserDict.class)
    @RequestMapping(method = RequestMethod.DELETE)
    @RequiresPermissions(value = {Permission.USER_DEL})
    public Object remove(@RequestParam Long userId) {
        if (userId == null) {
            throw new ApplicationException(BizExceptionEnum.REQUEST_NULL);
        }
        if (userId.intValue() <= 1) {
            return Rets.failure("不能删除初始用户");
        }
        User user = userService.get(userId);
        user.setStatus(ManagerStatus.DELETED.getCode());
        userService.update(user);
        return Rets.success();
    }

    /**
     * 设置账号角色
     * @param userId
     * @param roleIds
     * @return
     */
    @BussinessLog(value = "设置账号角色", key = "userId", dict = UserDict.class)
    @RequestMapping(value = "/setRole", method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.USER_EDIT})
    public Object setRole(@RequestParam("userId") Long userId, @RequestParam("roleIds") String roleIds) {
        if (BeanUtil.isOneEmpty(userId, roleIds)) {
            throw new ApplicationException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能修改超级管理员
        if (userId.intValue() == Const.ADMIN_ID.intValue()) {
            throw new ApplicationException(BizExceptionEnum.CANT_CHANGE_ADMIN);
        }
        User user = userService.get(userId);
        user.setRoleid(roleIds);
        userService.update(user);
        return Rets.success();
    }

    /**
     * 冻结/解冻账号
     * @param userId
     * @return
     */
    @BussinessLog(value = "冻结/解冻账号", key = "userId", dict = UserDict.class)
    @RequestMapping(value = "changeStatus", method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.USER_EDIT})
    public Object changeStatus(@RequestParam Long userId) {
        if (userId == null) {
            throw new ApplicationException(BizExceptionEnum.REQUEST_NULL);
        }
        User user = userService.get(userId);
        user.setStatus(user.getStatus().intValue() == ManagerStatus.OK.getCode() ? ManagerStatus.FREEZED.getCode() : ManagerStatus.OK.getCode());
        userService.update(user);
        return Rets.success();
    }

    /**
     * 是否存在用户信息（用户名/手机号/邮箱）
     * @return
     */
    @RequestMapping(value = "isHave",method = RequestMethod.GET)
    public Object index(User user){
        Boolean isHave = false;
        User uObj = userService.getUserInfo(user);
        if(ToolUtil.isNotEmpty(uObj)){
            isHave = true;
        }
        return Rets.success(isHave);
    }




}
