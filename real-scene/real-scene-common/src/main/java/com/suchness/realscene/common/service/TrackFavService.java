package com.suchness.realscene.common.service;

import com.alibaba.fastjson.JSON;
import com.suchness.realscene.common.bean.dto.pipe.in.TrackFavDto;
import com.suchness.realscene.common.bean.dto.pipe.out.PathCruiseResult;
import com.suchness.realscene.common.bean.dto.pipe.out.PathFavResult;
import com.suchness.realscene.common.dao.pipe.TrackFavRepository;
import com.suchness.realscene.common.entity.pipe.PathFavCruise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangchan
 */
@Service
public class TrackFavService  extends BaseService<PathFavCruise, Integer, TrackFavRepository>{
    @Autowired
    private TrackFavRepository trackFavRepository;



    public PathFavCruise findById(int id) {
        PathFavCruise pathFavCruise = trackFavRepository.findById(id);
        return pathFavCruise;
    }





    public PathFavResult insertTrackFav(TrackFavDto dto) {
        PathFavCruise pathFavCruise = new PathFavCruise();
        pathFavCruise.setName(dto.getName());
        pathFavCruise.setRemarks(dto.getRemarks());
        pathFavCruise.setCoordinate(JSON.toJSONString(dto.getCoordinate()));
        PathFavCruise save = this.insert(pathFavCruise);
        PathFavResult pathFavResult = new PathFavResult(save, JSON.parseArray(save.getCoordinate()));
        return pathFavResult;
    }


    public List<PathFavResult> findAll() {
        List<PathFavResult> results = new ArrayList<>();
        List<PathFavCruise> list = trackFavRepository.findAll();
        for (PathFavCruise pathFavCruise : list) {
            PathFavResult pathFavResult = new PathFavResult(pathFavCruise, JSON.parseArray(pathFavCruise.getCoordinate()));
            results.add(pathFavResult);
        }
        //    System.out.println(results);
        return results;
    }
}
