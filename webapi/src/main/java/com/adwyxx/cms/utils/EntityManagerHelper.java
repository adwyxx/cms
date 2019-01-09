package com.adwyxx.cms.utils;

import javax.persistence.Query;
import java.util.Map;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/1/9 16:59
 */
public class EntityManagerHelper {

    /**
     * 给hql参数设置值
     * @param query 查询
     * @param params 参数
     */
    public static void setParameters(Query query, Map<String,Object> params){
        for(Map.Entry<String,Object> entry:params.entrySet()){
            query.setParameter(entry.getKey(),entry.getValue());
        }
    }
}
