package com.suchness.realscene.common.bean.constant.state;

/**
 * @author: rs
 * @date: 2020/6/20 9:00
 * @description:
 * 管理员的状态
 */
public enum ManagerStatus {

    OK(1, "启用"), FREEZED(2, "冻结"), DELETED(3, "被删除");

    Integer code;
    String message;

    ManagerStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String valueOf(Integer value) {
        if (value == null) {
            return "";
        } else {
            for (ManagerStatus ms : ManagerStatus.values()) {
                if (ms.getCode().equals(value)) {
                    return ms.getMessage();
                }
            }
            return "";
        }
    }
}
