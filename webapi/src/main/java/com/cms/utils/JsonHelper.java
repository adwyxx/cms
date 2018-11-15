package com.cms.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;


/**
 * @program: webapi
 * @description: JSON序列化工具类
 * @author: Leo
 * @create: 2018-11-09 14:51
 **/
public class JsonHelper
{
    public static SerializerFeature[] defaultSerializerFeature = new SerializerFeature[]{
            SerializerFeature.DisableCircularReferenceDetect,//消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
            SerializerFeature.WriteMapNullValue, //输出值为null的字段,默认为false
            SerializerFeature.WriteNullListAsEmpty, //将值为null的list输出为[]
            SerializerFeature.WriteNullBooleanAsFalse //将之为null的boolean类型输出为false
    };

    /**
    * @description: 将对象序列化成Json字符串
    * @param value: 对象
    * @return:  Json字符串
     * @author: Leo
    * @date: 2018/11/9
    **/
    public static String SerializeObject(Object value){
        if(value==null) {
            return "null";
        }
        return JSON.toJSONString(value);
    }

    /**
    * @description:  将对象序列化成Json字符串
    * @param value:  对象
    * @param features: 序列化配置
    * @return: Json字符串
    * @author: Leo
    * @date: 2018/11/9
    **/
    public static  String SerializeObject(Object value, SerializerFeature ...features) {
        if(value==null) {
            return "null";
        }
        if(features==null)
        {
            return JSON.toJSONString(value,defaultSerializerFeature);
        }
        return JSON.toJSONString(value,features);
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
        return JSON.parseObject(json, new TypeReference<T>(){});
    }
}
