package com.suchness.realscene.api.controller.api;


import com.alibaba.fastjson.JSONObject;
import com.suchness.realscene.common.bean.dto.pipe.in.BuildingDto;
import com.suchness.realscene.common.bean.dto.pipe.in.PipeDto;
import com.suchness.realscene.common.bean.dto.pipe.out.BuildingConditionsResult;
import com.suchness.realscene.common.bean.dto.pipe.out.BuildingResult;
import com.suchness.realscene.common.service.BuildingService;
import com.suchness.realscene.common.utils.StringUtil;
import com.suchness.realscene.common.vo.Rets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 建筑物接口api
 *
 * @Author wangchan
 * @Date 11:17 2020/8/28
 **/
@RestController
@RequestMapping("/api/building")
public class BuildingController {
    private Logger logger = LoggerFactory.getLogger(BuildingController.class);
    @Autowired
    private BuildingService buildingService;

    /**
     * 范围内建筑物查询
     *
     * @return java.lang.Object
     * @Param [map]
     **/
    @RequestMapping(value = "/getAttributes", method = RequestMethod.POST)
    @ResponseBody
    public Object getAttributes(@RequestBody Map<String, String> map) {
        try {
            List<BuildingResult> BuildingResult = buildingService.findById(map.get("Id"));
            JSONObject json = new JSONObject();
            json.put("data", BuildingResult.get(0));
            return Rets.success(json);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Rets.success("没有该建筑物");
        }
    }

    /**
     * 范围内建筑物属性查询
     *
     * @return java.lang.Object
     * @Param [dto]
     **/
    @RequestMapping(value = "/getRangeAttributes", method = RequestMethod.POST)
    @ResponseBody
    public Object queryRange(@RequestBody PipeDto dto) {
        try {
            List<BuildingResult> buildingResults = new ArrayList<>();
            JSONObject json = new JSONObject();
            if (dto.getIds().length == 0 || StringUtil.isNullOrEmpty(dto)) {
                json.put("records", buildingResults);
                return Rets.success(json);
            }
            buildingResults = buildingService.queryRange(dto.getIds());
            json.put("records", buildingResults);
            return Rets.success(json);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Rets.failure("没有选中建筑物");
        }
    }

    /**
     * 建筑物条件查询
     *
     * @return java.lang.Object
     * @Param [dto]
     **/
    @PostMapping(value = "/getConditionAttributes")
    @ResponseBody
    public Object getConditionAttributes(@RequestBody BuildingDto dto) {

        try {
            List<BuildingResult> buildingResults = buildingService.queryByParams(dto);
            JSONObject json = new JSONObject();
            json.put("records", buildingResults);
            return Rets.success(json);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Rets.failure("没有选中建筑物");
        }
    }

    /**
     * 获取建筑物的所有条件
     *
     * @return java.lang.Object
     * @Param []
     **/
    @PostMapping(value = "/getConditions")
    @ResponseBody
    public Object getConditions() {
        try {
            BuildingConditionsResult buildingResults = buildingService.queryAllConditions();
            JSONObject json = new JSONObject();
            json.put("condtions", buildingResults);
            return Rets.success(json);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Rets.failure("没有选中建筑物");
        }
    }


}
