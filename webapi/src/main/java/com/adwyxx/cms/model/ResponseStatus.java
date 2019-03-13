package com.adwyxx.cms.model;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/11 15:09
 */
public enum ResponseStatus {
    TEST(1,"test"),
    SUCCESS(200, "请求成功"),
    LOGIN_FAIL(401, "登录失败"),
    LOGOUT_SUCCESS(104,"登出成功"),
    USER_NOT_FOUND(102,"未注册用户"),
    USER_DISABLED(103,"无效用户"),
    NO_LOGIN(105,"未登录"),
    VERIFY_CODE_ERROR(106,"验证码错误"),
    SYSTEM_ERROR(500, "系统错误");

    private int code;
    private String message;

    public int getCode(){
        return code;
    }
    public String getMessage(){
        return message;
    }

    ResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
