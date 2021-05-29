package com.suchness.realscene.common.vo.node;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/***
 *  author: wch
 *  create_time : 2020/6/16 13:35
 *******/
@Data
public class Node {

    private Long id;
    private Long pid;
    private String name;
    private Boolean checked;
    private List<Node> children = new ArrayList<>(10);
}
