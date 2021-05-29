package com.suchness.realscene.common.entity.pipe;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: rs
 * @date: 2020/7/8 9:51
 * @description:
 * 企业实体
 */
@Entity(name = "company")
@Data
@Table(appliesTo = "company",comment = "探测单位")
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(name ="autoGen",allocationSize=1,initialValue=15,sequenceName ="company_id_seq")
public class Company  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="autoGen")
    @Column(name = "id")
    private Integer id;
    @Column(columnDefinition = "varchar(120) COMMENT '企业名称'")
    private String name;
    @Column(name = "english_name",columnDefinition = "varchar(120) COMMENT '企业名称'")
    private String englishName;
    @Column(columnDefinition = "varchar(255) COMMENT '地址'")
    private String address;
    @Column(columnDefinition = "varchar(60) COMMENT '联系人'")
    private String userName;
    @Column(columnDefinition = "varchar(24) COMMENT '手机号'")
    private String phone;
    @Column(columnDefinition = "varchar(32) COMMENT '传真'")
    private String fax;
    @Column(columnDefinition = "varchar(32) COMMENT '邮箱'")
    private String email;
    @Column(name = "land_line",columnDefinition = "varchar(32) COMMENT '座机号'")
    private String landLine;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreatedDate
    @Column(name = "create_time",columnDefinition="DATETIME COMMENT '创建时间/注册时'",updatable = false)
    private Date createTime;

    @Column(name = "create_by",columnDefinition="bigint COMMENT '创建人'",updatable = false)
    @CreatedBy
    private Long createBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @LastModifiedDate
    @Column(name = "modify_time",columnDefinition="DATETIME COMMENT '最后更新时间'")
    private Date modifyTime;

    @LastModifiedBy
    @Column(name = "modify_by",columnDefinition="bigint COMMENT '最后更新人'")
    private Long modifyBy;

}
