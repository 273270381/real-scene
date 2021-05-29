package com.suchness.realscene.common.bean.dto.pipe.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Author wangchan
 * @Date 16:43 2020/8/28
 * @Description 建筑物查询映射后类型
**/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildingType {
    private String imagePath;
    private String name;
    private String type;
    private String community;
    private String road;
    private String phone;
    private String brief;
    private String id;

}
