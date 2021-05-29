package com.suchness.realscene.api.controller.info;

import com.suchness.realscene.api.controller.BaseController;
import com.suchness.realscene.common.SearchFilter;
import com.suchness.realscene.common.bean.constant.factory.PageFactory;
import com.suchness.realscene.common.entity.info.Camera;
import com.suchness.realscene.common.enums.Permission;
import com.suchness.realscene.common.service.CameraService;
import com.suchness.realscene.common.utils.StringUtil;
import com.suchness.realscene.common.utils.ToolUtil;
import com.suchness.realscene.common.utils.factory.Page;
import com.suchness.realscene.common.vo.Rets;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: rs
 * @date: 2020/12/1 17:23
 * @description:
 * 摄像头控制器
 */
@RestController
@RequestMapping("/camera")
@Slf4j
public class CameraController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CameraService cameraService;


    /**
     * 摄像头信息列表
     * @param address
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.CAMERA})
    public Object list(@RequestParam(required = false) String address){
        Page<Camera> page = new PageFactory<Camera>().defaultPage();
        if(StringUtil.isNotEmpty(address)){
            page.addFilter(SearchFilter.build("address", SearchFilter.Operator.LIKE, address));
        }
        page = cameraService.queryPage(page);
        return Rets.success(page);
    }

    /**
     * 摄像头添加
     * @param camera
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @RequiresPermissions(value = {Permission.CAMERA_EDIT})
    public Object save(Camera camera){
        if(ToolUtil.isOneEmpty(camera)){
            return Rets.failure("参数不能为空，请重试。");
        }

        if(StringUtil.isNullOrEmpty(camera.getId())){
            cameraService.insert(camera);
        }else{
            cameraService.update(camera);
        }
        return Rets.success();
    }

    /**
     * 删除信息
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @RequiresPermissions(value = {Permission.CAMERA_DEL})
    public Object delete(@RequestParam Integer id){
        cameraService.delete(id);
        return Rets.success();
    }

}
