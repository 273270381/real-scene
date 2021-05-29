package com.suchness.realscene.common.bean.dto.pipe.out;

import lombok.Data;

import java.math.BigInteger;

/**
 * @Author wangchan
 * @Date 16:46 2020/8/28
 * @Description 管点特征分类接口返回类型
**/

@Data
public class PipeNodeFeatureResult {

    private String type;
    private String features;
    private int count;
}
