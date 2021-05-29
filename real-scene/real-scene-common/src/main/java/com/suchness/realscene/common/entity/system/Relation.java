package com.suchness.realscene.common.entity.system;

import lombok.Data;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/***
 *  author: wch
 *  create_time : 2020/6/16 10:06
 *******/
@Entity(name = "t_sys_relation")
@Table(appliesTo = "t_sys_relation",comment = "菜单角色关系")
@Data
public class Relation {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Long menuid;
    @Column
    private Long roleid;
}
