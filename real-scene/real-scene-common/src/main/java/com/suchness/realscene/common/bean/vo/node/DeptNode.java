package com.suchness.realscene.common.bean.vo.node;

import com.suchness.realscene.common.entity.system.Dept;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: rs
 * @date: 2020/6/30 9:43
 * @description:
 * 部门节点
 */
public class DeptNode extends Dept {

    private List<DeptNode> children = new ArrayList<>(10);

    public List<DeptNode> getChildren() {
        return children;
    }

    public void setChildren(List<DeptNode> children) {
        this.children = children;
    }
}
