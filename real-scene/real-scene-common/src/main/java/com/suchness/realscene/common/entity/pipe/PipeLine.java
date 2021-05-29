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
 *  operation : pipeline entity
 *******/
@Entity(name = "pipe_line")
@Table(appliesTo = "pipe_line",comment = "管线")
@Data
@EntityListeners(AuditingEntityListener.class)
public class PipeLine  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="auto_gen")
    @SequenceGenerator(name ="auto_gen",allocationSize=1,sequenceName ="pipe_line_id_seq")
    @Column(name = "gid")
    private Integer id;
    @Column(name = "__gid")
    @Excel(name = "ID",type = Excel.Type.IMPORT)
    private Integer gid;
    private Integer dno;
    private Date crttime;
    private Date modtime;
    private String crtuser;
    private String moduser;
    private String guid;
    private Integer ogid;
    private Integer stnod;
    private Integer ednod;
    @Excel(name = "record_id")
    @Column(columnDefinition = "varchar(200) COMMENT '关键字'")
    private String record_id;
    @Column(columnDefinition = "varchar(200) COMMENT '起点号'")
    @Excel(name = "起点号")
    private String start_point;
    @Column(columnDefinition = "varchar(200) COMMENT '终点号'")
    @Excel(name = "终点号")
    private String end_point;
    @Column(columnDefinition = "varchar(200) COMMENT '起点埋深'")
    @Excel(name = "起点埋深")
    private String start_depth;
    @Column(columnDefinition = "varchar(200) COMMENT '终点埋深'")
    @Excel(name = "终点埋深")
    private String end_depth;
    @Column(columnDefinition = "INT(32) COMMENT '埋设类型' default 0")
    private Integer fill_type_id;
    @Column(columnDefinition = "INT(32) COMMENT '材质' default 0")
    private Integer material_id;
    @Column(columnDefinition = "varchar(200) COMMENT '管径'")
    @Excel(name = "管径")
    private String pipe_diameter;
    @Column(columnDefinition = "varchar(200) COMMENT '流向'")
    @Excel(name = "流向")
    private String flow_direction;
    @Column(columnDefinition = "varchar(200) COMMENT '电压压力'")
    @Excel(name = "电压压力")
    private String voltage_pressure;
    @Column(columnDefinition = "INT(32) COMMENT '电缆条数' default 0")
    @Excel(name = "电缆条数")
    private Integer cable_number;
    @Column(columnDefinition = "INT(32) COMMENT '总孔数' default 0")
    @Excel(name = "总孔数")
    private Integer hole_number;
    @Column(columnDefinition = "INT(32) COMMENT '分配孔数' default 0")
    @Excel(name = "分配孔数")
    private Integer hole_allocate_number;
    @Excel(name = "建设年代")
    @JsonFormat(
            pattern = "yyyy",
            timezone = "GMT+8"
    )
    @DateTimeFormat(pattern="yyyy")
    private Date build_date;
    @Column(columnDefinition = "INT(32) COMMENT '已用孔数' default 0")
    @Excel(name = "已用孔数")
    private Integer hole_used_number;
    @Column(columnDefinition = "numeric(6) COMMENT '套管尺寸'")
    @Excel(name = "套管尺寸")
    private Double pipe_size;
    @Column(columnDefinition = "varchar(200) COMMENT '保护材料'")
    @Excel(name = "保护材料")
    private String material_protect;
    @Column(columnDefinition = "numeric(6) COMMENT '起点管顶高'")
    @Excel(name = "起点管顶高程")
    private Double start_pipe_top_height;
    @Column(columnDefinition = "numeric(6) COMMENT '终点管顶高'")
    @Excel(name = "终点管顶高程")
    private Double end_pipe_top_height;
    @Column(columnDefinition = "numeric(6) COMMENT '管线权属代'")
    @Excel(name = "管线权属代码")
    private Integer pipeline_ownership;
    @Column(columnDefinition = "varchar(200) COMMENT '道路名称'")
    @Excel(name = "道路名称")
    private String road_name;
    @Column(columnDefinition = "varchar(200) COMMENT '备注'")
    @Excel(name = "备注")
    private String remarks;
    @Column(columnDefinition = "numeric(32) COMMENT '起点地面高'")
    @Excel(name = "起点地面高程")
    private Double start_ground_height;
    @Column(columnDefinition = "numeric(32) COMMENT '终点地面高'")
    @Excel(name = "终点地面高程")
    private Double end_ground_height;
    @Column(columnDefinition = "INT(32) COMMENT '探测单位' default 0")
    private Integer detect_company_id;
    @Column(columnDefinition = "INT(32) COMMENT '监理单位' default 0")
    private Integer supervise_company_id;
    @Column(columnDefinition = "date(0) COMMENT '探测日期'")
    @Excel(name = "探测日期")
    @JsonFormat(
            pattern = "yyyy-MM",
            timezone = "GMT+8"
    )
    @DateTimeFormat(pattern="yyyy-MM")
    private Date detect_date;
    @Column(columnDefinition = "INT(32) COMMENT '管线类型' default 0")
    private Integer type;
    @Column(columnDefinition = "INT(32) COMMENT '权属单位' default 0")
    private Integer ownership_company_id;
    @Column(columnDefinition = "varchar(200) COMMENT '辅助类型'")
    private String assist_type;
    private String lnumber;
    @Column(columnDefinition = "varchar(96) COMMENT '探测视频'")
    private String detect_video;
    private Double start_pipe_bottom_height;
    private Double end_pipe_bottom_height;
    @Column(columnDefinition = "numeric(6) COMMENT '终点高程'")
    private Double start_altitude;
    @Column(columnDefinition = "numeric(32) COMMENT '终点高程'")
    private Double end_altitude;

    private String geom;


    @JoinColumn(name = "detect_company_id" , referencedColumnName = "id" , insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Company company;
    @JoinColumn(name = "start_point" , referencedColumnName = "probe_point" , insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private PipeNode pipeNode;
    @JoinColumn(name = "end_point", referencedColumnName = "probe_point", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private PipeNode pipeNode2;

    @Transient
    @Excel(name = "埋设类型")
    private String fillType;
    @Transient
    @Excel(name = "材质")
    private String material;
    @Transient
    @Excel(name = "管线段类型")
    private String typeName;

    @Transient
    @Excel(name = "类型")
    private String pipeLineTypeName;

    @Transient
    @Excel(name = "探测单位")
    private String detectCompany;
    @Transient
    @Excel(name = "监理单位")
    private String superviseCompany;
    @Transient
    @Excel(name = "权属单位")
    private String ownershipCompany;


}


