package com.suchness.realscene.common.entity.pipe;

import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * @Author wangchan
 * @Date 15:46 2020/8/28
 * @Description 轨迹实体类
**/
@Entity(name = "path_cruise")
@Table(appliesTo = "path_cruise",comment = "轨迹")
@Data
@EntityListeners(AuditingEntityListener.class)
public class PathCruise implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="auto_gen")
    @SequenceGenerator(name ="auto_gen",allocationSize=1,sequenceName ="path_cruise_id")
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    private double speed;
    private double groundDistance;
    private String coordinate;

}
