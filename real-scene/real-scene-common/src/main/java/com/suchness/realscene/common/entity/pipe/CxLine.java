package com.suchness.realscene.common.entity.pipe;

import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "cx_lin")
@Data
@Table(appliesTo = "cx_lin",comment = "电信管线")
@EntityListeners(AuditingEntityListener.class)
public class CxLine {
    @Id
    @Column
    private Integer gid;
    @Column
    private Integer dno;
    @Column
    private Date crttime;
    @Column
    private Date modtime;
    @Column
    private String crtuser;
    @Column
    private String moduser;
    @Column
    private String guid;
    @Column
    private Integer ogid;
    @Column
    private Integer stnod;
    @Column
    private Integer ednod;

}
