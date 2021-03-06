package com.suchness.realscene.common.bean.constant.state;

/**
 * @description 业务是否成功的日志记录
 * @author hejunfeng
 * @time 2020/7/10 0010
 */
public enum LogSucceed {

    SUCCESS("成功"),
    FAIL("失败");

    String message;

    LogSucceed(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
