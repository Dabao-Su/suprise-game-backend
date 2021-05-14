package com.dabao.global.enums;

/**
 * Created by dabao on 2021-01-07.
 */
public enum ResultEnum {
    SUCCESS(200, "成功"),
    NOT_FOUND(404, "未找到该资源"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    SERVER_BUSY(503, "服务器繁忙，请稍后重试");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
