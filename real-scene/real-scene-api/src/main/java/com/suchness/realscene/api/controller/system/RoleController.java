package com.suchness.realscene.api.controller.system;

import com.suchness.realscene.common.SearchFilter;
import com.suchness.realscene.common.bean.constant.Const;
import com.suchness.realscene.common.bean.constant.factory.PageFactory;
import com.suchness.realscene.common.bean.core.BussinessLog;
import com.suchness.realscene.common.bean.dictmap.RoleDict;
import com.suchness.realscene.common.entity.system.Role;
import com.suchness.realscene.common.entity.system.User;
import com.suchness.realscene.common.enums.BizExceptionEnum;
import com.suchness.realscene.common.enums.Permission;
import com.suchness.realscene.common.exception.ApplicationException;
import com.suchness.realscene.common.service.RoleService;
import com.suchness.realscene.common.service.system.LogObjectHolder;
import com.suchness.realscene.common.service.system.UserService;
import com.suchness.realscene.common.service.system.impl.ConstantFactory;
import com.suchness.realscene.common.utils.*;
import com.suchness.realscene.common.utils.factory.Page;
import com.suchness.realscene.common.vo.Rets;
import com.suchness.realscene.common.vo.node.Node;
import com.suchness.realscene.common.vo.node.ZTreeNode;
import com.suchness.realscene.common.warpper.RoleWarpper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


 /**
  * @Author wuchanghao
  * @Description  role operation
  * @Date  2020/6/16 8:50
  **/
@RestController
@RequestMapping("/role")
public class RoleController {

    private Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

     /**
      * @Author wuchanghao
      * @Description show roles list
      * @Date
      * @Param name
      * @return Object
      **/
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.ROLE})
    public Object list(@RequestParam(required = false) String name){
        Page page = new PageFactory().defaultPage();
        page.addFilter("name", SearchFilter.Operator.LIKE, name);
        page = roleService.queryPage(page);
        List roles = (List) new RoleWarpper(BeanUtil.objectsToMaps(page.getRecords())).warp();
        page.setRecords(roles);
        return Rets.success(page);
    }

     /**
      * @Author wuchanghao
      * @Description get all roles
      * @Date
      * @Param
      * @return Object
      **/
    @RequestMapping(value = "/allRoles",method = RequestMethod.GET)
    public Object allRoles(){
        List roles = roleService.queryAll();
        return Rets.success(new RoleWarpper(BeanUtil.objectsToMaps(roles)).warp());
    }

     /**
      * @Author wuchanghao
      * @Description add role or edit role
      * @Date
      * @Param
      * @return Object
      **/
    @RequestMapping(method = RequestMethod.POST)
    @RequiresPermissions(value = {Permission.ROLE_EDIT})
    @BussinessLog(value = "编辑角色", key = "name", dict = RoleDict.class)
    public Object save(@Valid Role role){
        if(role.getId() == null){
            roleService.insert(role);
        }else{
            roleService.update(role);
        }
        return Rets.success();
    }

     /**
      * @Author wuchanghao
      * @Description delete role
      * @Date
      * @Param roleIds
      * @return Object
      **/
    @DeleteMapping("/{roleIds}")
    @RequiresPermissions(value = {Permission.ROLE_DEL})
    @BussinessLog(value = "删除角色", key = "roleIds", dict = RoleDict.class)
    public Object remove(@PathVariable Long[] roleIds){
        logger.info("roleIds:{}",roleIds);
        if (roleIds==null) {
            throw new ApplicationException(BizExceptionEnum.REQUEST_NULL);
        }
        for(Long roleId : roleIds){
            if(roleId.intValue()<2){
                return Rets.failure("不能删除初始角色");
            }
            List<User> userList = userService.queryAll(SearchFilter.build("roleid",SearchFilter.Operator.EQ,String.valueOf(roleId)));
            if(!userList.isEmpty()){
                return Rets.failure("有用户使用该角色，禁止删除");
            }
            //不能删除超级管理员角色
            if(roleId.intValue() ==Const.ADMIN_ROLE_ID){
                return Rets.failure("禁止删除超级管理员角色");
            }
            //缓存被删除的角色名称
            LogObjectHolder.me().set(ConstantFactory.me().getSingleRoleName(roleId));
            roleService.delRoleById(roleId);
        }
        return Rets.success();
    }

     /**
      * @Author wuchanghao
      * @Description set role permissions
      * @Date
      * @Param roleId,permissions
      * @return Object
      **/
    @RequestMapping(value = "/savePermisson",method = RequestMethod.POST)
    @BussinessLog(value = "配置角色权限", key = "roleId", dict = RoleDict.class)
    @RequiresPermissions(value = {Permission.ROLE_EDIT})
    public Object setAuthority( Long roleId, String permissions){
        if (BeanUtil.isOneEmpty(roleId)) {
            throw new ApplicationException(BizExceptionEnum.REQUEST_NULL);
        }
        System.out.println("-----------------------------------------");
        roleService.setAuthority(roleId, permissions);
        return Rets.success();
    }

     /**
      * @Author wuchanghao
      * @Description get role tree
      * @Date
      * @Param idUser
      * @return Object
      **/
    @RequestMapping(value = "/roleTreeListByIdUser", method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.ROLE})
    public Object roleTreelistByIdUser(Long idUser){
        User user =  userService.get(idUser);
        String roleIds = user.getRoleid();
        List<ZTreeNode> roleTreeList;
        if(StringUtil.isEmpty(roleIds)){
            roleTreeList = roleService.roleTreeList();
        }else{
            Long[] roleArray = Convert.toLongArray(",", roleIds);
            roleTreeList = roleService.roleTreeListByRoleId(roleArray);
        }

        List<Node> list = roleService.generateRoleTree(roleTreeList);
        List<Long> checkedIds = Lists.newArrayList();
        for(ZTreeNode zTreeNode : roleTreeList){
            if(zTreeNode.getChecked() != null && zTreeNode.getChecked()){
                checkedIds.add(zTreeNode.getId());
            }
        }
        return Rets.success(Maps.newHashMap("treeData", list, "checkedIds", checkedIds));
    }

     /**
      * @Author wuchanghao
      * @Description get role detail by roleId
      * @Date
      * @Param roleId
      * @return Object
      **/
    @RequestMapping(value = "/{roleId}",method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.ROLE})
    public Object getRole(@PathVariable(value = "roleId") Long roleId){
            return Rets.success( roleService.get(roleId));
    }


}
