package com.suchness.realscene.common.entity.system;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "t_sys_login_log")
@Table(appliesTo = "t_sys_login_log",comment = "登陆日志")
@Data
public class LoginLog {
    @Id
    @GeneratedValue
    private Integer id;
    @CreationTimestamp
    @Column(name = "create_time",columnDefinition="DATETIME COMMENT '创建时间'")
    private Date createTime;
    @Column
    private String ip;
    @Column
    private String logname;
    @Column
    private String message;
    @Column
    private String succeed;
    @Column
    private Integer userid;
}
