package com.adwyxx.cms.config;

import com.adwyxx.cms.model.ResponseModel;
import com.alibaba.fastjson.JSON;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/11 16:10
 */
@ControllerAdvice
public class BaseGlobalResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    //这个方法表示对于哪些请求要执行beforeBodyWrite，返回true执行，返回false不执行
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    //对于返回的对象如果不是最终对象ResponseResult，则选包装一下
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
                                  Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(!(o instanceof ResponseModel)) {
            ResponseModel model = new ResponseModel(o);
            //因为handler处理类的返回类型是String，为了保证一致性，这里需要将ResponseResult转回去
            if(o instanceof String) {
                return JSON.toJSONString(model);
            }
            return model;
        }
        return o;
    }
}
