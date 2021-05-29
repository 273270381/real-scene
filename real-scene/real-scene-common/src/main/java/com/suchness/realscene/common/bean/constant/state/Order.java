package com.suchness.realscene.common.bean.constant.state;

/**
 * @author: rs
 * @date: 2020/6/18 9:10
 * @description:
 * 数据库排序
 */
public enum Order {

    ASC("asc"), DESC("desc");

    private String des;

    Order(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
