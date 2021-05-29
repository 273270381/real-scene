package com.suchness.realscene.common.bean.dto.pipe.in;


import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author hejunfeng
 * @Description 轨迹管理dto
 * @Date 10:09 2020/8/28 0028
 * @Param
 * @return
 **/
@Data
public class TrackDto {


    String name;
    double speed;
    double ground_distance;
    List<Map<String,Double>> coordinate ;
}
