package com.suchness.realscene.common.bean.dto.pipe.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author hejunfeng
 * @Date 14:40 2020/8/28 0028
 * @Description 管线返回字段
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PipeLineResult {

    private String type;
    private String startDepth;
    private String endDepth;
    private Double startGroundHeight;
    private Double endGroundHeight;
    private String detectCompany;
    private String pipeDiameter;
    private String material;
    private String roadName;
    private String fillType;
    private Integer cableNumber;
    private String buildDate;
}
