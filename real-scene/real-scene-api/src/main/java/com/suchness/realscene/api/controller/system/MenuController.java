package com.suchness.realscene.api.controller.system;

import com.suchness.realscene.api.controller.BaseController;
import com.suchness.realscene.common.bean.ShiroUser;
import com.suchness.realscene.common.cache.TokenCache;

import com.suchness.realscene.common.entity.system.Menu;
import com.suchness.realscene.common.enums.BizExceptionEnum;
import com.suchness.realscene.common.enums.Permission;
import com.suchness.realscene.common.exception.ApplicationException;
import com.suchness.realscene.common.service.MenuService;
import com.suchness.realscene.common.utils.HttpUtil;
import com.suchness.realscene.common.utils.Lists;
import com.suchness.realscene.common.utils.Maps;
import com.suchness.realscene.common.vo.Rets;
import com.suchness.realscene.common.vo.node.MenuNode;
import com.suchness.realscene.common.vo.node.Node;
import com.suchness.realscene.common.vo.node.RouterMenu;
import com.suchness.realscene.common.vo.node.ZTreeNode;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @Author wuchanghao
 * @Description  menu operation
 * @Date  2020/6/17 8:50
 **/
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(MenuController.class);
    @Autowired
    private MenuService menuService;
    @Autowired
    private TokenCache tokenCache;

    /**
     * @Author wuchanghao
     * @Description show menu list
     * @Date
     * @Param
     * @return Object
     **/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list(){
        List<MenuNode> list = menuService.getMenus();
        return Rets.success(list);
    }

    /**
     * @Author wuchanghao
     * @Description show router list
     * @Date
     * @Param
     * @return Object
     **/
    @RequestMapping(value = "/listForRouter", method = RequestMethod.GET)
    public Object listForRouter(){
        ShiroUser shiroUser = tokenCache.getUser(HttpUtil.getToken());

        List<RouterMenu> list = menuService.getSideBarMenus(shiroUser.getRoleList());
        return Rets.success(list);
    }

    /**
     * @Author wuchanghao
     * @Description save menu or edit menu
     * @Date
     * @Param menu
     * @return Object
     **/
    @RequestMapping(method = RequestMethod.POST)
    @RequiresPermissions(value = {Permission.MENU_EDIT})
    public Object save(@ModelAttribute @Valid Menu menu){
        menuService.menuSetPcode(menu);
        if(menu.getId() == null){
            menuService.insert(menu);
        }else{
            menuService.update(menu);
        }
        return Rets.success();
    }

    /**
     * @Author wuchanghao
     * @Description delete menu
     * @Date
     * @Param id
     * @return Object
     **/
    @RequestMapping(method = RequestMethod.DELETE)
    @RequiresPermissions(value = {Permission.MENU_DEL})
    public Object remove(@RequestParam Long id){
        logger.info("id:{}", id);
        if (id == null) {
            throw new ApplicationException(BizExceptionEnum.REQUEST_NULL);
        }
        menuService.delMenuContainSubMenus(id);
        return Rets.success();
    }

    /**
     * @Author wuchanghao
     * @Description get menu tree by roleid
     * @Date
     * @Param roleId
     * @return Object
     **/
    @RequestMapping(value = "/menuTreeListByRoleId/{roleId}", method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.MENU})
    public Object menuTreeListByRoleId(@PathVariable(value = "roleId") Integer roleId){
        List<Long> menuIds = menuService.getMenuIdsByRoleId(roleId);
        List<ZTreeNode> roleTreeList;
        if(menuIds == null || menuIds.isEmpty()){
            roleTreeList = menuService.menuTreeList(null);
        }else{
            roleTreeList = menuService.menuTreeList(menuIds);
        }
        List<Node> list = menuService.generateMenuTreeForRole(roleTreeList);
        //element-ui中tree控件中如果选中父节点会默认选中所有子节点，所以这里将所有非叶子节点去掉
        Map<Long,ZTreeNode> map = Lists.toMap(roleTreeList,"id");
        Map<Long,List<ZTreeNode>> group = Lists.group(roleTreeList,"pId");
        for(Map.Entry<Long,List<ZTreeNode>> entry:group.entrySet()){
            if(entry.getValue().size()>1){
                roleTreeList.remove(map.get(entry.getKey()));
            }
        }

        List<Long> checkedIds = Lists.newArrayList();
        for (ZTreeNode zTreeNode : roleTreeList) {
            if (zTreeNode.getChecked() != null && zTreeNode.getChecked()
                    &&zTreeNode.getPId().intValue()!=0) {
                checkedIds.add(zTreeNode.getId());
            }
        }
        return Rets.success(Maps.newHashMap("treeData", list, "checkedIds", checkedIds));
    }


}
