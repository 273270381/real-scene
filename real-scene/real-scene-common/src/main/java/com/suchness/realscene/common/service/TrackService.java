package com.suchness.realscene.common.service;

import com.alibaba.fastjson.JSON;
import com.suchness.realscene.common.bean.dto.pipe.in.TrackDto;
import com.suchness.realscene.common.bean.dto.pipe.in.TrackFavDto;
import com.suchness.realscene.common.bean.dto.pipe.out.PathFavResult;
import com.suchness.realscene.common.dao.pipe.TrackRepository;
import com.suchness.realscene.common.entity.pipe.PathCruise;
import com.suchness.realscene.common.bean.dto.pipe.out.PathCruiseResult;
import com.suchness.realscene.common.entity.pipe.PathFavCruise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangchan
 */
@Service
public class TrackService extends BaseService<PathCruise, Integer, TrackRepository> {
    @Autowired
    private TrackRepository trackRepository;

    /**
     * @return com.suchness.realscene.common.entity.pipe.PathCruise
     * @Author wangchan
     * @Description //TODO 根据id 查询轨迹路径
     * @Date 9:47 2020/8/28
     * @Param [id]
     **/
    public PathCruise findById(int id) {
        PathCruise pathCruise = trackRepository.findById(id);
        return pathCruise;
    }

    /**
     * @Author wangchan
     * @Date 15:41 2020/8/28
     * @Param []
     * @Return java.util.List<com.suchness.realscene.common.bean.dto.pipe.out.PathCruiseResult>
     * @Description 查询所有轨迹
     **/
    public List<PathCruiseResult> findAll() {
        List<PathCruiseResult> results = new ArrayList<>();
        List<PathCruise> list = trackRepository.findAll();

        for (PathCruise pathCruise : list) {
            PathCruiseResult pathCruiseResult = new PathCruiseResult(pathCruise, JSON.parseArray(pathCruise.getCoordinate()));
            results.add(pathCruiseResult);
        }
        //    System.out.println(results);
        return results;
    }

    /**
     * @return void
     * @Author hejunfeng
     * @Date 13:30 2020/8/28 0028
     * @Param [pathCruise, name, speed, ground_distance]
     * @Description 轨迹更新
     **/
    public void updatePath(PathCruise pathCruise, String name, Double speed, Double ground_distance) {
        if (name != null) {
            pathCruise.setName(name);
        }
        if (speed != null) {
            pathCruise.setSpeed(speed);
        }
        if (ground_distance != null) {
            pathCruise.setGroundDistance(ground_distance);
        }
        trackRepository.save(pathCruise);
    }

    /**
     * @Author wangchan
     * @Date 16:00 2020/8/28
     * @Param [dto]
     * @Return void
     * @Description 添加轨迹
     **/
    public PathCruiseResult insertTrack(TrackDto dto) {
        PathCruise pathCruise = new PathCruise();
        pathCruise.setName(dto.getName());
        pathCruise.setSpeed(dto.getSpeed());
        pathCruise.setGroundDistance(dto.getGround_distance());
        pathCruise.setCoordinate(JSON.toJSONString(dto.getCoordinate()));
        PathCruise save = this.insert(pathCruise);
        PathCruiseResult pathCruiseResult = new PathCruiseResult(save, JSON.parseArray(save.getCoordinate()));
        return pathCruiseResult;
    }




}
