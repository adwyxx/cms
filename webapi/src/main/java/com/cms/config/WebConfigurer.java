package com.cms.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.cms.utils.HibernatePropertyFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description: Alibaba FastJson Configuration
 * @Auther: Leo.W
 * @Date: 2018/11/15 10:24
 */
/*
@Configuration
public class FastJsonConfiguration implements WebMvcConfigurer {

    //重写消息序列化配置
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        //将converters默认的JascksonConverter去掉
        Iterator<HttpMessageConverter<?>> iterator= converters.iterator();
        while (iterator.hasNext())
        {
            HttpMessageConverter<?> converter = iterator.next();
            if(converter instanceof MappingJackson2HttpMessageConverter)
            {
                iterator.remove();
            }
        }

        //创建配置类
        FastJsonConfig config = new FastJsonConfig();
        //设置日期格式
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        config.setSerializeFilters(new HibernatePropertyFilter());
        //设置JSON序列化格式
        config.setSerializerFeatures(
                SerializerFeature.DisableCircularReferenceDetect,//消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
                SerializerFeature.WriteMapNullValue, //输出值为null的字段,默认为false
                SerializerFeature.WriteNullListAsEmpty, //将值为null的list输出为[]
                SerializerFeature.WriteNullStringAsEmpty,//将值为null的字符串输出为‘’
                SerializerFeature.WriteNullBooleanAsFalse, //将之为null的boolean类型输出为false
                SerializerFeature.WriteDateUseDateFormat
        );

        //创建消息转换器
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //设置MediaType,处理中文乱码
        List<MediaType> fastMediaTypes = new ArrayList<MediaType>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        fastConverter.setFastJsonConfig(config);
        //在converters中添加FastJsonHttpMessageConverter
        converters.add(fastConverter);
    }
}
*/