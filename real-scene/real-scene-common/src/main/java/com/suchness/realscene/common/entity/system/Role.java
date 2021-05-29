package com.suchness.realscene.common.entity.system;

import com.suchness.realscene.common.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.validation.constraints.NotBlank;

/***
 *  author: wch
 *  create_time : 2020/6/16 9:02
 *  operation : role entity
 *******/
@Entity(name = "t_sys_role")
@Table(appliesTo = "t_sys_role",comment = "角色")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Role extends BaseEntity {
    @Column
    private Integer num;
    @Column
    private Long pid;
    @Column
    @NotBlank(message = "角色名称不能为空")
    private String name;
    @Column
    private Long deptid;
    @Column
    private String tips;
    @Column
    private Integer version;
}
