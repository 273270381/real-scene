package com.suchness.realscene.common.vo.node;

import com.suchness.realscene.common.utils.Lists;
import lombok.Data;

import java.util.List;

/***
 *  author: wch
 *  create_time : 2020/6/19 9:53
 *******/
@Data
public class RouterMenu {

    private Long id;
    private Long parentId;
    private String path;
    private String component;
    private String name;
    private Integer num;
    private Boolean hidden=false;
    private MenuMeta meta;
    private List<RouterMenu> children = Lists.newArrayList();

}
