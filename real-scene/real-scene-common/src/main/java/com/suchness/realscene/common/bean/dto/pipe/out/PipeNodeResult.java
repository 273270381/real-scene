package com.suchness.realscene.common.bean.dto.pipe.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Author hejunfeng
 * @Date 14:42 2020/8/28 0028
 * @Description 管点字段
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PipeNodeResult {
    private String type;
    private Double xCoordinates;
    private Double yCoordinates;
    private String appendages;
    private String detectCompany;
    private String features;
    private String manholeMaterial;
    private String manholeDiameter;
    private String holeDepth;
    private String roadName;
    private String buildDate;
}
