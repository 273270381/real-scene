package com.suchness.realscene.common.entity.pipe;

import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @Author wangchan
 * @Date 9:28 2020/9/23
 * @Param
 * @Return
 * @Description
**/
@Entity(name = "path_cruise_favourite")
@Table(appliesTo = "path_cruise_favourite",comment = "喜爱轨迹")
@Data
@EntityListeners(AuditingEntityListener.class)
public class PathFavCruise {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="auto_gen")
    @SequenceGenerator(name ="auto_gen",allocationSize=1,sequenceName ="path_cruise_favourite_id")
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;

    @Column(name = "remarks")
    private String remarks;
    private String coordinate;


   public PathFavCruise(Integer id ,String name,String remarks,String coordinate)
    {
        this.id=id;
        this.name=name;
        this.remarks=remarks;
        this.coordinate=coordinate;

    }

    public  PathFavCruise(){

    }
}
