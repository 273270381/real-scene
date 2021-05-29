package com.suchness.realscene.common.bean.dto.pipe.out;

import lombok.Data;

import java.math.BigInteger;

/**
 * @Author wangchan
 * @Date 16:45 2020/8/28
 * @Description 管线综合统计接口返回类型
**/
@Data
public class PipeComResult {

    private String type;
    private int count;
    private  double distance;
    private  String condition;
}
