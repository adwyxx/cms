package com.adwyxx.cms.config;

import com.adwyxx.cms.model.ErrorMsg;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/11 14:39
 */
@Component
public class WebApiInterceptor implements HandlerInterceptor {
    @Autowired
    private ResponseHolder responseHolder;

    /**
     * 进入对应的controller方法之前
     **/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    /**
     * controller处理之后，返回对应的视图之前
     **/
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 整个请求结束后调用，视图渲染后，主要用于资源的清理
     **/
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if(responseHolder == null){
            responseHolder.setErrorMsg(ErrorMsg.SYSTEM_ERROR);
        }
        responseHolder.getModel();
        String json = JSON.toJSONString(responseHolder.getModel());
        this.returnJson(response,json);
        responseHolder.clean();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    private void returnJson(HttpServletResponse response, String json) throws Exception {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {

        } finally {
            if (writer != null)
                writer.close();
        }

    }
}
