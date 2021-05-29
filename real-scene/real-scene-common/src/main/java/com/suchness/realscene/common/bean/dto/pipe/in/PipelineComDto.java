package com.suchness.realscene.common.bean.dto.pipe.in;


import lombok.Data;
/**
 * @Author wangchan
 * @Date 16:48 2020/8/28
 * @Description 管线综合统计接口参数
**/
@Data
public class PipelineComDto {


    private int[] types;
    private  String[]  lineIds;
    private  String condition;
}
