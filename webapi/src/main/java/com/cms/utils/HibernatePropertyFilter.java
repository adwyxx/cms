package com.cms.utils;

import com.alibaba.fastjson.serializer.PropertyFilter;
import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;
import org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor;

/**
 * @Description: FastJson序列化属性过滤器
 * @Auther: Leo.W
 * @Date: 2018/11/15 16:48
 */
public class HibernatePropertyFilter implements PropertyFilter {

    /**
    * @description : 过滤不需要被序列化的属性，主要是应用于Hibernate的代理和管理。
    * @param object : 属性所在的对象
    * @param name : 属性名
    * @param value : 属性值
    * @author : Leo.W
    * @date : 2018/11/15 16:52
    * @return : 返回false属性将被忽略，ture属性将被保留
    **/
    @Override
    public boolean apply(Object object, String name, Object value) {
        if(value.getClass().equals(ByteBuddyInterceptor.class))
        {
            return false;
        }
        else if (value instanceof HibernateProxy) {
            //hibernate代理对象
            LazyInitializer initializer = ((HibernateProxy) value).getHibernateLazyInitializer();
            if (initializer.isUninitialized()) {
                return false;
            }
        } else if (value instanceof PersistentCollection) {
            //实体关联集合一对多等
            PersistentCollection collection = (PersistentCollection) value;
            if (!collection.wasInitialized()) {
                return false;
            }
            Object val = collection.getValue();
            if (val == null) {
                return false;
            }
        }
        return true;
    }
}
