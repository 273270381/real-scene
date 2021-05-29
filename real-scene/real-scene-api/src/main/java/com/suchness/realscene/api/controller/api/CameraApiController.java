package com.suchness.realscene.api.controller.api;

import com.suchness.realscene.common.SearchFilter;
import com.suchness.realscene.common.bean.constant.factory.PageFactory;
import com.suchness.realscene.common.entity.info.Camera;
import com.suchness.realscene.common.service.CameraService;
import com.suchness.realscene.common.utils.StringUtil;
import com.suchness.realscene.common.utils.factory.Page;
import com.suchness.realscene.common.vo.Rets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author hejunfeng
 * @Date 10:58 2020/9/14 0014
 * @Description 视频接入controller
 **/
@RestController
@RequestMapping("/api/camera")
public class CameraApiController {
    private Logger logger = LoggerFactory.getLogger(CameraApiController.class);


    @Autowired
    private CameraService cameraService;



    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object getAllUrls(){
        try {
            List<Camera> cameras = cameraService.queryCameras();
            if (cameras.size() == 0) {
                return Rets.success(new ArrayList<>());
            }
            return Rets.success(cameras);
        }catch (Exception e){
            return Rets.failure("查询失败");
        }
    }


    /**
     * 获取摄像头分页信息
     * @param address
     * @return
     */
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public Object list(@RequestParam(required = false) String address){
        Page<Camera> page = new PageFactory<Camera>().defaultPage();
        if(StringUtil.isNotEmpty(address)){
            page.addFilter(SearchFilter.build("address", SearchFilter.Operator.LIKE, address));
        }
        page = cameraService.queryPage(page);
        Map<String,Object> map = new HashMap<>();
        map.put("list",page.getRecords());
        map.put("limit",page.getLimit());
        map.put("total",page.getTotal());
        map.put("current",page.getCurrent());
        map.put("pages",page.getPages());
        return Rets.success(map);
    }


}
