package com.suchness.realscene.common.entity.system;

import com.suchness.realscene.common.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/***
 *  author: wch
 *  create_time : 2020/6/12 14:06
 *******/
@Entity(name = "t_sys_user")
@Table(appliesTo = "t_sys_user",comment = "账号")
@Data
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity {
    @Column
    private String avatar;
    @Column(columnDefinition = "VARCHAR(32) COMMENT '账户'")
    private String account;
    @Column(columnDefinition = "VARCHAR(64) COMMENT '密码'")
    private String password;
    @Column(columnDefinition = "VARCHAR(16) COMMENT '密码盐'")
    private String salt;
    @Column(columnDefinition = "VARCHAR(64) COMMENT '姓名'")
    private String name;
    @Column
    private Date birthday;
    @Column
    private Integer sex;
    @Column(columnDefinition = "VARCHAR(64) COMMENT 'email'")
    private String email;
    @Column(columnDefinition = "VARCHAR(16) COMMENT '手机号'")
    private String phone;
    @Column(columnDefinition = "VARCHAR(128) COMMENT '角色id列表，以逗号分隔'")
    private String roleid;
    @Column
    private Long deptid;
    @Column(columnDefinition = "int(11) COMMENT '状态：1启用 2冻结  3删除'")
    private Integer status;
    @Column
    private Integer version;
    @JoinColumn(name="deptid", insertable = false, updatable = false,foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.EAGER)
    private Dept dept;

    @Column(columnDefinition = "VARCHAR(120) COMMENT '地址'")
    private String address;
}
