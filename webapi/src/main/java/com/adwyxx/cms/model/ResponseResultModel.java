package com.adwyxx.cms.model;

import org.hibernate.proxy.HibernateProxy;
import java.io.Serializable;

/**
 * @Description: Http请求返回结果信息模型
 * @Auther: Leo.W
 * @Date: 2018/11/15 17:34
 */
public class ResponseResultModel implements Serializable {
    private static final long serialVersionUID = 7788978918244491761L;
    //请求结果状态，success/failed
    private String status;
    //请求失败后返回信息
    private String message;
    //请求成功后返回结果
    private Object data;

    public ResponseResultModel()
    {
        this.status="sccuess";
    }

    public String getStatus() {
        return status;
    }

    public ResponseResultModel setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseResultModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResponseResultModel setData(Object data) {
        //如果返回数据类型是hibernate代理对象则获取其实体对象作为返回数据，否则FastJson序列号会报错
        if (data instanceof HibernateProxy) {
            this.data=((HibernateProxy) data).getHibernateLazyInitializer().getImplementation();
        }
        else
        {
            this.data = data;
        }

        return this;
    }
}
