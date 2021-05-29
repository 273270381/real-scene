package com.suchness.realscene.common.bean.dto.pipe.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author wangchan
 * @Date 16:46 2020/8/28
 * @Param
 * @Return
 * @Description 管线类型查询接口返回类型
**/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PipeLineTypeResult {
    private  int id;
    private  String name;
}
