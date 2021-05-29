package com.suchness.realscene.common.entity.system;

import com.suchness.realscene.common.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * @author: rs
 * @date: 2020/7/2 8:45
 * @description: 字典实体Bean
 */
@Entity(name = "t_sys_dict")
@Table(appliesTo = "t_sys_dict", comment = "字典")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Dict extends BaseEntity {

    @Column(columnDefinition = "VARCHAR(255) COMMENT '编号'")
    private String num;

    @Column(columnDefinition = "bigint COMMENT '父节点'")
    private Long pid;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '名称'")
    private String name;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '提示信息'")
    private String tips;
}
