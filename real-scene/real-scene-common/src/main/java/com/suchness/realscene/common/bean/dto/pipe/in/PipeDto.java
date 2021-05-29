package com.suchness.realscene.common.bean.dto.pipe.in;

import lombok.Data;

import java.util.List;
/**
 * @Author wangchan
 * @Date 16:48 2020/8/28
 * @Description 范围内建筑物属性查询接口参数
**/

@Data
public class PipeDto {

    private String[] ids;
    private  int  limit;
    private  int offset;

}
