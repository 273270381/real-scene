package com.suchness.realscene.common.entity.info;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
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
 * @date: 2020/12/2 14:36
 * @description: Created by hejunfeng on 2020/9/14 0014
 *
 */
@Entity(name = "camera")
@Table(appliesTo = "camera",comment = "视频")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Camera implements Serializable {

    @Id
    @GeneratedValue
    @GenericGenerator(name = "system", strategy = "identity")
    private Integer id;

//    @Column(columnDefinition = "VARCHAR(255) COMMENT '名称'")
//    private String name;

    @Column(columnDefinition = "VARCHAR(60) COMMENT '类型名称' default '海康球机' ")
    private String typeName;

    @Column(columnDefinition = "VARCHAR(255) COMMENT 'url'")
    private String url;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '地址'")
    private String address;

    @Column(columnDefinition = "VARCHAR(16) COMMENT 'ip地址'")
    private String ip;

    @Column(columnDefinition = "VARCHAR(8) COMMENT '端口'")
    private String port;

    @Column(columnDefinition = "VARCHAR(16) COMMENT '云台ip地址'")
    private String cloudIp;

    @Column(columnDefinition = "VARCHAR(8) COMMENT '云台端口'")
    private String cloudPort;

    @Column(columnDefinition = "VARCHAR(120) COMMENT '用户名'")
    private String loginName;

    @Column(columnDefinition = "VARCHAR(120) COMMENT '密码'")
    private String loginPassword;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreatedDate
    @Column(name = "create_time",columnDefinition="DATETIME COMMENT '创建时间/注册时'",updatable = false)
    private Date createTime;

    @Column(name = "create_by",columnDefinition="bigint COMMENT '创建人'",updatable = false)
    @CreatedBy
    @JsonIgnore
    private Long createBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @LastModifiedDate
    @Column(name = "modify_time",columnDefinition="DATETIME COMMENT '最后更新时间'")
    @JsonIgnore
    private Date modifyTime;

    @LastModifiedBy
    @Column(name = "modify_by",columnDefinition="bigint COMMENT '最后更新人'")
    @JsonIgnore
    private Long modifyBy;


}
