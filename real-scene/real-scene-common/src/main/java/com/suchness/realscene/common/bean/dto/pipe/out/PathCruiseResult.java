package com.suchness.realscene.common.bean.dto.pipe.out;

import com.suchness.realscene.common.entity.pipe.PathCruise;
import lombok.Data;

import java.util.List;
import java.util.Map;



/**
 * @Author wangchan
 * @Date 16:44 2020/8/28
 * @Description  轨迹路径返回类型
**/
@Data
public class PathCruiseResult {

    Integer id;
    String name;
    double speed;
    double groundDistance;
    Object coordinate ;
    public  PathCruiseResult(){

    }
    public PathCruiseResult(PathCruise pathCruise,Object coordinate){
         this.coordinate=coordinate;
         this.id=pathCruise.getId();
         this.name=pathCruise.getName();
         this.speed=pathCruise.getSpeed();
         this.groundDistance=pathCruise.getGroundDistance();
    }
}
