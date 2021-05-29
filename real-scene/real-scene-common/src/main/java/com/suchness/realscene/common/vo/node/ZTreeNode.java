package com.suchness.realscene.common.vo.node;

import lombok.Data;

/***
 *  author: wch
 *  create_time : 2020/6/16 11:15
 *******/
@Data
public class ZTreeNode {
    //节点id
    private Long id;
    //父节点id
    private Long pId;
    //节点名称
    private String name;
    //是否打开节点
    private Boolean open;
    //是否被选中
    private Boolean checked;
    //自定义数据
    private Object nodeData;

    public static ZTreeNode createParent(){
        ZTreeNode zTreeNode = new ZTreeNode();
        zTreeNode.setChecked(true);
        zTreeNode.setId(0L);
        zTreeNode.setName("顶级");
        zTreeNode.setOpen(true);
        zTreeNode.setPId(0L);
        return zTreeNode;
    }
}
