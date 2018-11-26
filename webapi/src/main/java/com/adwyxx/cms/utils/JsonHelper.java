package com.adwyxx.cms.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;


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
    * @description :将HibernateProxy实体转换为真实的实体
    * @param entity: 实体信息
    * @author : Leo.W
    * @date : 2018/11/20 16:23
    * @return :真实的实体
    **/
    public static Object unHibernateProxy(Object entity)
    {
        //如果返回数据类型是hibernate代理对象则获取其实体对象作为返回数据，否则FastJson序列号会报错
        if (entity instanceof HibernateProxy) {
            return ((HibernateProxy) entity).getHibernateLazyInitializer().getImplementation();
        }
        return entity;
    }
    /**
    * @description: 将对象序列化成Json字符串
    * @param value: 对象
    * @return:  Json字符串
     * @author: Leo
    * @date: 2018/11/9
    **/
    public static String serializeObject(Object value){
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
    public static  String serializeObject(Object value, SerializerFeature ...features) {
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
    public static <T> T deserializeObject(String json)
    {
        if(json==null || json.isEmpty()) {
            return null;
        }
        return JSON.parseObject(json, new TypeReference<T>(){});
    }


    /**
     * @description : 将Hibernate代理实体初始化成实际实体
     * @param  entity : 实际实体
     * @author : Leo.W
     * @date : 2018/11/20 16:19
     * @return :
     **/
    public static <T> T initializeAndUnproxy(T entity) {
        if (entity == null) {
            throw new NullPointerException("Entity passed for initialization is null");
        }

        Hibernate.initialize(entity);
        if (entity instanceof HibernateProxy) {
            entity = (T) ((HibernateProxy) entity)
                    .getHibernateLazyInitializer()
                    .getImplementation();
        }
        return entity;
    }
}
