package com.suchness.realscene.common.bean.dto.pipe.in;

import lombok.Data;

import java.util.List;
/**
 * @Author wangchan
 * @Date 16:49 2020/8/28
 * @Description 管线管径分类接口参数
**/
@Data
public  class PipeLineDto {


    private String[] lineIds;
    private  int[]  types;


}
