package com.suchness.realscene.api.controller.system;

import com.suchness.realscene.api.controller.BaseController;
import com.suchness.realscene.common.bean.core.BussinessLog;
import com.suchness.realscene.common.bean.dictmap.DictMap;
import com.suchness.realscene.common.entity.system.Dict;
import com.suchness.realscene.common.enums.BizExceptionEnum;
import com.suchness.realscene.common.enums.Permission;
import com.suchness.realscene.common.exception.ApplicationException;
import com.suchness.realscene.common.service.system.DictService;
import com.suchness.realscene.common.utils.BeanUtil;
import com.suchness.realscene.common.utils.StringUtil;
import com.suchness.realscene.common.vo.Rets;
import com.suchness.realscene.common.warpper.DictWarpper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: rs
 * @date: 2020/7/2 8:44
 * @description:
 * 字典控制器
 */
@RestController
@RequestMapping("/dict")
public class DictController extends BaseController {

    @Autowired
    private DictService dictService;

    /**
     * 获取所有字典列表
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.DICT})
    public Object list(String name) {

        if(StringUtil.isNotEmpty(name)){
            List<Dict> list = dictService.findByNameLike(name);
            return Rets.success(new DictWarpper(BeanUtil.objectsToMaps(list)).warp());
        }
        List<Dict> list = dictService.findByPid(0L);
        return Rets.success(new DictWarpper(BeanUtil.objectsToMaps(list)).warp());
    }

    /**
     * 添加字典
     * @param dictName
     * @param dictValues
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @BussinessLog(value = "添加字典", key = "dictName",dict= DictMap.class)
    @RequiresPermissions(value = {Permission.DICT_EDIT})
    public Object add(String dictName, String dictValues) {
        if (BeanUtil.isOneEmpty(dictName, dictValues)) {
            throw new ApplicationException(BizExceptionEnum.REQUEST_NULL);
        }
        dictService.addDict(dictName, dictValues);
        return Rets.success();
    }

    /**
     * 修改字典
     * @param id  id
     * @param dictName  名称
     * @param dictValues   值
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    @BussinessLog(value = "修改字典", key = "dictName",dict=DictMap.class)
    @RequiresPermissions(value = {Permission.DICT_EDIT})
    public Object update(Long id,String dictName, String dictValues) {
        if (BeanUtil.isOneEmpty(dictName, dictValues)) {
            throw new ApplicationException(BizExceptionEnum.REQUEST_NULL);
        }
        dictService.editDict(id,dictName, dictValues);
        return Rets.success();
    }

    /**
     * 删除字典
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @BussinessLog(value = "删除字典", key = "id",dict=DictMap.class)
    @RequiresPermissions(value = {Permission.DICT_EDIT})
    public Object delete(@RequestParam Long id) {
        dictService.delteDict(id);
        return Rets.success();
    }

}
