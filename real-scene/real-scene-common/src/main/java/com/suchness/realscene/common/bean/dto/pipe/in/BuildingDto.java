package com.suchness.realscene.common.bean.dto.pipe.in;


import lombok.Data;

/**
 * @Author wangchan
 * @Date 16:47 2020/8/28
 * @Description 建筑物条件查询接口参数
**/
@Data
public class BuildingDto {

   private String name;
    private String type;

    private String community;
    private  String road;

    private  int  limit;
    private  int offset;
}
