package com.suchness.realscene.common.bean.dto.pipe.out;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author hejunfeng
 * @Date 14:38 2020/8/28 0028
 * @Description 管线材质分析类
 **/
@Data
public class pipeLineMaterialResult implements Serializable {

    private String type;
    private String pipe_material;
    private Integer count;
    private double distance;
}
