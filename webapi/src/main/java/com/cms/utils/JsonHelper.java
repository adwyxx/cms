package com.cms.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @program: webapi
 * @description: JSON序列化工具类
 * @author: Leo
 * @create: 2018-11-09 14:51
 **/
public class JsonHelper
{
    private static ObjectMapper defaultMapper= new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)  //类级别的设置，JsonInclude.Include.NON_EMPTY标识只有非NULL的值才会被纳入json string之中，其余的都将被忽略
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES) //禁止使用出现未知属性之时，抛出异常
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);//转化后的json的key命名格式

    /**
    * @description: 将对象序列化成Json字符串
    * @param value: 对象
    * @return:  Json字符串
     * @author: Leo
    * @date: 2018/11/9
    **/
    public static String SerializeObject(Object value) throws JsonProcessingException {
        if(value==null) {
            return "null";
        }
        return defaultMapper.writeValueAsString(value);
    }

    /**
    * @description:  将对象序列化成Json字符串
    * @param value:  对象
    * @param mapper: ObjectMapper
    * @return: Json字符串
    * @author: Leo
    * @date: 2018/11/9
    **/
    public static  String SerializeObject(Object value,ObjectMapper mapper) throws JsonProcessingException {
        if(value==null) {
            return "null";
        }
        if(mapper==null)
        {
            return defaultMapper.writeValueAsString(value);
        }
        else
        {
            return mapper.writeValueAsString(value);
        }
    }
    /**
    * @description: Json反序列化
    * @param json: Json字符串
    * @return:  反序列化的实体类信息
    * @author: Leo
    * @date: 2018/11/9
    **/
    public static <T> T DeserializeObject(String json)
    {
        if(json==null || json.isEmpty()) {
            return null;
        }
        return defaultMapper.convertValue(json, new TypeReference<T>(){});
    }
}
