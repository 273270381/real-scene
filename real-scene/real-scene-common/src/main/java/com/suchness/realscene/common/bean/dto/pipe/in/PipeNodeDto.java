package com.suchness.realscene.common.bean.dto.pipe.in;

import lombok.Data;

/**
 * @Author wangchan
 * @Description 管点特征分类接口参数
 * @Date 10:32 2020/8/28
**/
@Data
public class PipeNodeDto {

    private String[] nodeRecordIds;
    private  int[]  types;

}
