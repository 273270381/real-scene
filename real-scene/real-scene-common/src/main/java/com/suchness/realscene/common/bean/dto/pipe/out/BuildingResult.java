package com.suchness.realscene.common.bean.dto.pipe.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author wangchan
 * @Date 16:42 2020/8/28
 * @Param
 * @Return
 * @Description 建筑物接口返回类型
**/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildingResult {


    private String id;


    private String name;
    private String type;
    private String community;
    private String road;

    private String phone;
    private String brief;
    private String[] img_path;
}
