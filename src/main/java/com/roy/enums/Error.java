package com.roy.enums;

/**
 * description：
 * author：dingyawu
 * date：created in 23:29 2020/8/20
 * history:
 */
public enum Error {
    SYSO_ERROR("1100", "系统异常"),
    PARAM_ERROR("1000", "参数错误");
    private String code;
    private String msg;

    private Error(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
