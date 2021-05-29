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
 *  create_time : 2020/6/12 14:10
 *******/
@Entity(name = "t_sys_dept")
@Table(appliesTo = "t_sys_dept", comment = "部门")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Dept extends BaseEntity {

    @Column(columnDefinition = "int(11) COMMENT '编号'")
    private Integer num;

    @Column(columnDefinition = "bigint COMMENT '父节点ID'")
    private Long pid;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '父节点IDs'")
    private String pids;

    @Column
    @NotBlank(message = "部门简称不能为空")
    private String simplename;

    @Column
    @NotBlank(message = "部门全称不能为空")
    private String fullname;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '提示信息'")
    private String tips;

    @Column(columnDefinition = "int(11) COMMENT '版本(暂未用)'")
    private Integer version;
}
