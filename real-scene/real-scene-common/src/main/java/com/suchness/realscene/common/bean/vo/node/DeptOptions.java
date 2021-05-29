package com.suchness.realscene.common.bean.vo.node;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.suchness.realscene.common.entity.system.Dept;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: rs
 * @date: 2020/7/22 10:48
 * @description:
 * 部门节点
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DeptOptions implements Serializable {

    private static final long serialVersionUID = -1560603887556641494L;

    private Long value;

    private String label;

    private Long pid;


    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<DeptOptions> children = new ArrayList<>();


    public DeptOptions() {
    }

    public DeptOptions(Dept dept) {
        this.value = dept.getId();
        this.label = dept.getSimplename();
        this.pid = dept.getPid();
    }

    public DeptOptions(Long value, String label, List<DeptOptions> children) {
        this.value = value;
        this.label = label;
        this.children = children;
    }
}
