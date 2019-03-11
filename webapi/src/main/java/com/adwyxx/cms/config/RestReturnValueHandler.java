package com.adwyxx.cms.config;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/11 15:40
 */
@Component
public class RestReturnValueHandler implements HandlerMethodReturnValueHandler {

    private static final String STATUS_CODE_SUCCEEDED = "200";
    private static final String STATUS_CODE_INTERNAL_ERROR = "500";

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        if (returnType.hasMethodAnnotation(ResponseBody.class)
                || (!returnType.getDeclaringClass().equals(ModelAndView.class))
                && returnType.getMethod().getDeclaringClass().isAnnotationPresent(RestController.class)) {
            return true;
        }

        return false;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {
        mavContainer.setRequestHandled(true);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", STATUS_CODE_SUCCEEDED);
        resultMap.put("data", returnValue);
        resultMap.put("message", "");
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        //fastJsonHttpMessageConverter().write(resultMap, MediaType.APPLICATION_JSON_UTF8, new ServletServerHttpResponse(response));
    }

}
