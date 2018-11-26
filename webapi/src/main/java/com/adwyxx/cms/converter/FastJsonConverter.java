package com.adwyxx.cms.converter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: FastJson Converter
 * @Auther: Leo.W
 * @Date: 2018/11/15 16:00
 */
@Configuration
public class FastJsonConverter {

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverter()
    {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 创建配置类
        FastJsonConfig config = new FastJsonConfig();
        List<MediaType> fastMediaTypes = new ArrayList<MediaType>();
        // 处理中文乱码问题
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        fastConverter.setDefaultCharset(Charset.forName("UTF-8"));
        //设置日期格式
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置JSON序列化格式
        config.setSerializerFeatures(
                SerializerFeature.PrettyFormat,
                SerializerFeature.DisableCircularReferenceDetect,//消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
                SerializerFeature.WriteMapNullValue, //输出值为null的字段,默认为false
                SerializerFeature.WriteNullListAsEmpty, //将值为null的list输出为[]
                //SerializerFeature.WriteNullStringAsEmpty,//将值为null的字符串输出为‘’
                SerializerFeature.WriteNullBooleanAsFalse, //将值为null的boolean类型输出为false
                SerializerFeature.WriteNullNumberAsZero,//将值为null的数字类型默认输出为0
                SerializerFeature.WriteDateUseDateFormat
        );

        //config.setSerializeFilters(new HibernatePropertyFilter());
        fastConverter.setFastJsonConfig(config);

        //字符串类型的HTTP MESSAGE CONVERTER
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setDefaultCharset(Charset.forName("UTF-8"));
        stringConverter.setSupportedMediaTypes(fastMediaTypes);

        return new HttpMessageConverters(stringConverter,fastConverter);
    }
}
