package com.suchness.realscene.common.bean.dto.pipe.out;

import com.suchness.realscene.common.entity.pipe.PathFavCruise;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author wangchan
 * @Date 9:26 2020/9/23
 * @Param
 * @Return
 * @Description
**/
 @Data
public class PathFavResult {
    Integer id;
    String name;
    String remarks ;
    Object coordinate ;

    public PathFavResult(PathFavCruise pathFavCruise, Object coordinate){
        this.id=pathFavCruise.getId();
        this.name=pathFavCruise.getName();
        this.coordinate=coordinate;
       this.remarks=pathFavCruise.getRemarks();
    }
}
