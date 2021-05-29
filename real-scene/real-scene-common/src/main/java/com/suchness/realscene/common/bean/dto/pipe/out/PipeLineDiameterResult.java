package com.suchness.realscene.common.bean.dto.pipe.out;

import lombok.Data;

import java.math.BigInteger;

/**
 * @Author wangchan
 * @Date 16:45 2020/8/28
 * @Description  管线管径分类接口返回类型
**/

@Data
public class PipeLineDiameterResult {
    private String type;
    private String pipe_diameter;
    private int count;
    private double distance;
}
