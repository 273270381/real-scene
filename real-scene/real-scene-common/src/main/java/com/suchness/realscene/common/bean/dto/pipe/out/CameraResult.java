package com.suchness.realscene.common.bean.dto.pipe.out;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Data
public class CameraResult implements Serializable {

    private Integer id;

    private String typeName;

    private String url;

    private String address;

    private String ip;

    private String port;

    private String cloudIp;

    private String cloudPort;

    private String loginName;

    private String loginPassword;

    private Date createTime;

}
