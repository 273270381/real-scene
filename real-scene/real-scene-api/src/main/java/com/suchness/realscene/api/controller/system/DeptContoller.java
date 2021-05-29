package com.suchness.realscene.api.controller.system;

import com.suchness.realscene.api.controller.BaseController;
import com.suchness.realscene.common.bean.core.BussinessLog;
import com.suchness.realscene.common.bean.dictmap.DeptDict;
import com.suchness.realscene.common.bean.vo.node.DeptNode;
import com.suchness.realscene.common.bean.vo.node.DeptOptions;
import com.suchness.realscene.common.entity.system.Dept;
import com.suchness.realscene.common.enums.BizExceptionEnum;
import com.suchness.realscene.common.enums.Permission;
import com.suchness.realscene.common.exception.ApplicationException;
import com.suchness.realscene.common.service.system.DeptService;
import com.suchness.realscene.common.service.system.LogObjectHolder;
import com.suchness.realscene.common.utils.BeanUtil;
import com.suchness.realscene.common.utils.ToolUtil;
import com.suchness.realscene.common.vo.Rets;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * @author: rs
 * @date: 2020/6/30 9:35
 * @description:
 * 部门管理控制器
 */
@RestController
@RequestMapping("/dept")
public class DeptContoller extends BaseController {
    private Logger logger = LoggerFactory.getLogger(DeptContoller.class);

    @Autowired
    private DeptService deptService;

    /**
     * 部门列表
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.DEPT})
    public Object list(){
        List<DeptNode> list = deptService.queryAllNode();
        return Rets.success(list);
    }

    /**
     * 编辑/保存部门信息
     * @param dept
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @BussinessLog(value = "编辑部门", key = "simplename", dict = DeptDict.class)
    @RequiresPermissions(value = {Permission.DEPT_EDIT})
    public Object save(@ModelAttribute @Valid Dept dept){
        if (BeanUtil.isOneEmpty(dept, dept.getSimplename())) {
            throw new ApplicationException(BizExceptionEnum.REQUEST_NULL);
        }

        //部门ID不为空，此时是编辑
        if(dept.getId()!=null){
            Dept old = deptService.get(dept.getId());
            LogObjectHolder.me().set(old);
            old.setPid(dept.getPid());
            old.setSimplename(dept.getSimplename());
            old.setFullname(dept.getFullname());
            old.setNum(dept.getNum());
            old.setTips(dept.getTips());
            deptService.deptSetPids(old);
            deptService.update(old);
        }else {
            //判断名称和全称是否有重复
            Dept dept1 = deptService.getDeptByName(dept);
            if(ToolUtil.isNotEmpty(dept1)){
                return Rets.failure("部门名称重复，不能添加");
            }

            deptService.deptSetPids(dept);
            deptService.insert(dept);
        }
        return Rets.success();
    }

    /**
     * 删除部门
     * @param id 部门ID
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @BussinessLog(value = "删除部门", key = "id", dict = DeptDict.class)
    @RequiresPermissions(value = {Permission.DEPT_DEL})
    public Object remove(@RequestParam  Long id){
        logger.info("id:{}",id);
        if (id == null) {
            throw new ApplicationException(BizExceptionEnum.REQUEST_NULL);
        }
        deptService.deleteDept(id);
        return Rets.success();
    }

    /**
     * 获取部门下拉框选项
     * @return
     */
    @RequestMapping(value = "/optionsList",method = RequestMethod.GET)
    public Object optionsList(){
        List<DeptOptions> list = deptService.queryOptionsList();
        return Rets.success(list);
    }

    /**
     * 树形
     * @return
     */
    @RequestMapping(value = "/treeselect",method = RequestMethod.GET)
    public Object treeselect(){
        return Rets.success(deptService.buildDeptTreeSelect());
    }

    /**
     * 获取部门信息
     * @param deptId 部门ID
     * @return
     */
    @RequestMapping(value = "/getDept/{deptId}",method = RequestMethod.GET)
    public Object getDeptById(@PathVariable(value = "deptId") Integer deptId){
        return Rets.success(deptService.get(deptId.longValue()));
    }

}
