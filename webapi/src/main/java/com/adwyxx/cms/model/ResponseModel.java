package com.adwyxx.cms.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @Description: Http请求返回结果信息模型
 * @Auther: Leo.W
 * @Date: 2018/11/15 17:34
 */
public class ResponseModel implements Serializable {
    private static final long serialVersionUID = 7788978918244491761L;

    private ResponseStatus status;

    private String message;

    private Object data;

    private Locale locale;

    public ResponseModel(){
        this.status = ResponseStatus.SUCCESS;
        this.locale = Locale.CHINA;
    }

    public ResponseModel(Object data){
        this.data=data;
        this.status = ResponseStatus.SUCCESS;
        this.locale = Locale.CHINA;
    }

    public ResponseModel put(String key, Object value){
        if(this.data == null || !(data instanceof Map)){
            this.data = new HashMap<String,Object>();
        }
        Map<String, Object> map = (Map<String,Object>)this.data;
        map.put(key,value);
        return this;
    }

    public Object getData(){
        return data;
    }

    public ResponseModel setData(Object data){
        this.data = data;
        return this;
    }

    public ResponseModel setStatus(ResponseStatus status) {
        this.status = status;
        return this;
    }
    public ResponseModel setMessage(String message){
        this.message = message;
        return this;
    }

    public ResponseStatus getStatus() {
        return status;
    }
    public String getMessage(){
        return message;
    }
}
