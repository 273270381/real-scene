package com.suchness.realscene.common.bean.dto.pipe.in;

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
public class TrackFavDto {
    String name;
    String remarks ;
    List<Map<String,Double>> coordinate ;
}
