package com.suchness.realscene.common.entity.pipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "building")
@Data
@Table(appliesTo = "building", comment = "建筑物")
@EntityListeners(AuditingEntityListener.class)


@AllArgsConstructor
@NoArgsConstructor
public class Building {
    @Id
    @Column
    private String id;
    @Column(columnDefinition = "VARCHAR(120) COMMENT '建筑物名称'")
    private String name;
    @Column(columnDefinition = "VARCHAR(24) COMMENT '类型(例如：科技公司)'")
    private String type;
    @Column(columnDefinition = "VARCHAR(120) COMMENT '社区'")
    private String community;
    @Column(columnDefinition = "VARCHAR(120) COMMENT '道路'")
    private String road;
    @Column(columnDefinition = "VARCHAR(24) COMMENT '电话'")
    private String phone;
    @Column(columnDefinition = "VARCHAR(255) COMMENT '简介'")
    private String brief;

}