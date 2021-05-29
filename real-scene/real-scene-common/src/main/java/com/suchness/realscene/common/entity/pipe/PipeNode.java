package com.suchness.realscene.common.entity.pipe;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.suchness.realscene.common.utils.poi.Excel;
import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/***
 *  author: wch
 *  create_time : 2020/6/17 8:54
 *  operation : pipenode entity
 *******/
@Entity(name = "pipe_node")
@Table(appliesTo = "pipe_node",comment = "管点")
@Data
@EntityListeners(AuditingEntityListener.class)
public class PipeNode implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="auto_gen")
    @SequenceGenerator(name ="auto_gen",allocationSize=1,sequenceName ="pipe_node_id_seq")
    @Column(name = "gid")
    //@Excel(name="序号",type = Excel.Type.EXPORT)
    private Integer id;
    @Column(name = "__gid")
    @Excel(name = "ID",type = Excel.Type.IMPORT)
    private Integer gid;
    private Integer dno;
    private Integer angle;
    @Column(columnDefinition = "date(32) COMMENT '创建日期'")
    private Date crttime;
    @Column(columnDefinition = "date(32) COMMENT '修改日期'")
    private Date modtime;
    @Column(columnDefinition = "varchar(32) COMMENT '创建人'")
    private String crtuser;
    @Column(columnDefinition = "varchar(32) COMMENT '修改人'")
    private String moduser;
    private String guid;
    private Integer ogid;
    @Column(columnDefinition = "varchar(200) COMMENT '物探点号'")
    @Excel(name = "物探点号")
    private String probe_point;
    @Column(columnDefinition = "varchar(200) COMMENT '特征'")
    @Excel(name = "特征")
    private String features;
    @Column(columnDefinition = "varchar(200) COMMENT '附属物'")
    @Excel(name = "附属物")
    private String appendages;
    @Column(columnDefinition = "varchar(200) COMMENT 'x坐标'")
    @Excel(name = "X")
    private Float x_coordinates;
    @Column(columnDefinition = "varchar(200) COMMENT 'y坐标'")
    @Excel(name = "Y")
    private Float y_coordinates;
    @Column(columnDefinition = "varchar(200) COMMENT '符号旋转角'")
    @Excel(name = "符号旋转")
    private String symbol_angle;
    @Column(columnDefinition = "varchar(200) COMMENT '地面高程'")
    @Excel(name = "地面高程")
    private String ground_elevation;
    @Column(columnDefinition = "INT(32) COMMENT '权属单位ID'")
    private Integer ownership_unit;
    @Column(columnDefinition = "date(32) COMMENT '建设年代'")
    @Excel(name = "建设年代")

    @JsonFormat(
            pattern = "yyyy",
            timezone = "GMT+8"
    )
    @DateTimeFormat(pattern="yyyy")
    private Date build_date;
    @Column(columnDefinition = "varchar(200) COMMENT '道路名称'")
    @Excel(name = "道路名称")
    private String road_name;
    @Column(columnDefinition = "varchar(200) COMMENT '图幅号'")
    @Excel(name = "图幅号")
    private String map_number;
    @Column(columnDefinition = "varchar(200) COMMENT '井盖材料'")
    @Excel(name = "井盖材料")
    private String manhole_material;
    @Column(columnDefinition = "varchar(200) COMMENT '井盖直径'")
    @Excel(name = "井盖直径")
    private String manhole_diameter;
    private Integer manhole_type;
    @Column(columnDefinition = "varchar(200) COMMENT '偏心井位'")
    @Excel(name = "偏心井位")
    private String manhole_px;
    @Column(columnDefinition = "varchar(200) COMMENT '备注'")
    @Excel(name = "备注")
    private String remarks;
    @Column(columnDefinition = "varchar(200) COMMENT '井底深'")
    @Excel(name = "井底深")
    private String hole_depth;
    @Column(columnDefinition = "INT(32) COMMENT '探测单位ID'")

    private Integer detection_unit;
    @Column(columnDefinition = "INT(32) COMMENT '监理单位'")

    private Integer supervisor_unit;
    @Column(columnDefinition = "date(32) COMMENT '探测日期'")
    @Excel(name = "探测日期")
    @JsonFormat(
            pattern = "yyyy-MM",
            timezone = "GMT+8"
    )
    @DateTimeFormat(pattern="yyyy-MM")
    private Date detection_date;
    private Integer type;
    @Column(columnDefinition = "varchar(200) COMMENT '记录ID'")
    private String record_id;
    @JoinColumn(name = "detection_unit" , referencedColumnName = "id" , insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Company company;

    @Transient
    @Excel(name = "管线点类型")
    private String typeName;

    @Transient
    @Excel(name = "类型")
    private String pipeNodeType;

    @Transient
    @Excel(name = "权属单位")
    private String ownershipUnitName;
    @Transient
    @Excel(name = "井盖类型")
    private String manholeTypeName;
    @Transient
    @Excel(name = "探测单位")
    private String detectionUnitName;
    @Transient
    @Excel(name = "监理单位")
    private String supervisorUnitName;



}
