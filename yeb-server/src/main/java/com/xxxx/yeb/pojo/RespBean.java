package com.xxxx.yeb.pojo;

import lombok.*;

/**
 * 公共返回对象
 *
 * @author DongHui
 * @since 1.0.0
 */

public class RespBean {


    private Integer code;

    private String message;

    private Object obj;

    public RespBean() {
    }

    public RespBean(Integer code, String message, Object obj) {
        this.code = code;
        this.message = message;
        this.obj = obj;
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

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    /**
     * 返回成功对象
     *
     * @param message
     * @return
     */
    public static RespBean success(String message) {
        return new RespBean(200, message, null);
    }


    /**
     * 返回成功对象
     *
     * @param message
     * @param obj
     * @return
     */
    public static RespBean success(String message, Object obj) {
        return new RespBean(200, message, obj);
    }


    /**
     * 返回失败对象
     *
     * @param message
     * @return
     */
    public static RespBean error(String message) {
        return new RespBean(500, message, null);
    }


    /**
     * 返回失败对象
     *
     * @param message
     * @param obj
     * @return
     */
    public static RespBean error(String message, Object obj) {
        return new RespBean(500, message, obj);
    }


}