package com.suchness.realscene.common.bean.dto.pipe.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author wangchan
 * @Date 16:38 2020/8/28
 * @Description 建筑物的所有条件接口返回类型
**/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildingConditionsResult {
       private List<String> types;
       private  List<String> comminitys;
       private  List<String> roads;

}
