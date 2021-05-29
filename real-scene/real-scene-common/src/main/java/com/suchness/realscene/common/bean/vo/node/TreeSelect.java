package com.suchness.realscene.common.bean.vo.node;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.suchness.realscene.common.entity.system.Dept;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/***
 *  author: rushi
 *  create_time : 2020/7/2216:35
 *******/
public class TreeSelect implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 节点ID */
    private Long id;

    /** 节点名称 */
    private String label;



    /** 子节点 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeSelect> children ;



    public TreeSelect()
    {

    }

    public TreeSelect(DeptNode dept)
    {
        this.id = dept.getId();
        this.label = dept.getSimplename();
        this.children = dept.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public List<TreeSelect> getChildren()
    {
        return children;
    }

    public void setChildren(List<TreeSelect> children)
    {
        this.children = children;
    }


}
