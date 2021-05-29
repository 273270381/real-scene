package com.suchness.realscene.api.controller.api;

import com.alibaba.fastjson.JSON;
import com.suchness.realscene.common.bean.dto.pipe.in.TrackDto;
import com.suchness.realscene.common.bean.dto.pipe.in.TrackFavDto;
import com.suchness.realscene.common.bean.dto.pipe.out.PathFavResult;
import com.suchness.realscene.common.entity.pipe.PathCruise;
import com.suchness.realscene.common.bean.dto.pipe.out.PathCruiseResult;
import com.suchness.realscene.common.entity.pipe.PathFavCruise;
import com.suchness.realscene.common.service.TrackFavService;
import com.suchness.realscene.common.service.TrackService;
import com.suchness.realscene.common.vo.Rets;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author wangchan
 * @Description //TODO 轨迹管理api
 * @Date 9:38 2020/8/28
 **/
@RestController
@RequestMapping("/api/trackApi")
@Slf4j
public class TrackController {
    private Logger logger = LoggerFactory.getLogger(TrackController.class);

    @Autowired
    private TrackService trackService;
    @Autowired
    private TrackFavService trackFavService;

    /**
     * @Author wangchan
     * @Date 15:47 2020/8/28
     * @Param [dto]
     * @Return java.lang.Object
     * @Description 轨迹管理添加
     **/
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestBody TrackDto dto) {
        try {
            PathCruiseResult save=  trackService.insertTrack(dto);
            return Rets.success(save);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Rets.success("存储失败");
        }
    }

    /**
     * @Author wangchan
     * @Date 15:47 2020/8/28
     * @Param [map]
     * @Return java.lang.Object
     * @Description 轨迹管理删除
     **/
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestBody Map<String, Integer> map) {
        try {
            int id = map.get("id").intValue();
            PathCruise pathCruise = trackService.findById(id);
            if (pathCruise != null) {
                trackService.delete(id);
                return Rets.success("删除成功");
            }
            return Rets.success("id不存在");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Rets.failure("查询失败");
        }
    }


    /**
     * @return java.lang.Object
     * @Author hejunfeng
     * @Date 13:38 2020/8/28 0028
     * @Param [map]
     * @Description 轨迹管理更新
     **/
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@RequestBody Map<String, Object> map) {
        try {
            Integer id = (Integer) map.get("id");
            String name = (String) map.get("name");
            Double speed = Double.parseDouble(String.valueOf(map.get("speed")));
            Double groundDistance = Double.parseDouble(String.valueOf(map.get("groundDistance")));
            PathCruise pathCruise = trackService.findById(id);
            if (pathCruise != null) {
                trackService.updatePath(pathCruise, name, speed, groundDistance);
                return Rets.success("更新成功");
            }
            return Rets.failure("id不存在");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Rets.failure("更新失败");
        }

    }

    /**
     * @Author wangchan
     * @Date 15:48 2020/8/28
     * @Param []
     * @Return java.lang.Object
     * @Description 查询所有轨迹
     **/
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public Object query() {
        try {
            List<PathCruiseResult> ls = trackService.findAll();
            return Rets.success(ls);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Rets.failure("查询失败");
        }
    }

    /**
     * @Author wangchan
     * @Date 10:28 2020/9/23
     * @Param [dto]
     * @Return java.lang.Object
     * @Description
    **/
    @RequestMapping(value = "/addFav", method = RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestBody TrackFavDto dto) {
        try {
            PathFavResult save=  trackFavService.insertTrackFav(dto);
            return Rets.success(save);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Rets.success("存储失败");
        }
    }

    @RequestMapping(value = "/queryAllFav", method = RequestMethod.POST)
    @ResponseBody
    public  Object queryAllFav(){
        try {
            List<PathFavResult> ls=  trackFavService.findAll();
            return Rets.success(ls);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Rets.success("存储失败");
        }
    }




    /**
     * @Author wangchan
     * @Date 15:47 2020/8/28
     * @Param [map]
     * @Return java.lang.Object
     * @Description 轨迹管理删除
     **/
    @RequestMapping(value = "/deleteFav", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteFav(@RequestBody Map<String, Integer> map) {
        try {
            int id = map.get("id").intValue();
            PathFavCruise pathFavCruise = trackFavService.findById(id);
            if (pathFavCruise != null) {
                trackFavService.delete(id);
                return Rets.success("删除成功");
            }
            return Rets.success("id不存在");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Rets.failure("查询失败");
        }
    }



    @RequestMapping(value = "/updateFav", method = RequestMethod.POST)
    @ResponseBody
    public Object updateFav(@RequestBody Map<String, Object> map) {
        try {
            Integer id = (Integer) map.get("id");
            String name = (String) map.get("name");
            String remarks = (String) map.get("remarks");
            String coordinate = JSON.toJSONString(map.get("coordinate"));

            PathFavCruise pathFavCruise = trackFavService.findById(id);
            if (pathFavCruise != null) {

                PathFavCruise pathFavCruise1 = trackFavService.update(new PathFavCruise(id, name, remarks, coordinate));
                return Rets.success(new PathFavResult(pathFavCruise1,JSON.parseArray(pathFavCruise1.getCoordinate())));
            }
            return Rets.failure("id不存在");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Rets.failure("更新失败");
        }

    }


}
