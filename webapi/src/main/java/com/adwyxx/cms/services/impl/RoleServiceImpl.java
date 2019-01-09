package com.adwyxx.cms.services.impl;

import com.adwyxx.cms.entities.Role;
import com.adwyxx.cms.model.PaginationDataModel;
import com.adwyxx.cms.repositories.RoleRespository;
import com.adwyxx.cms.services.RoleService;
import com.adwyxx.cms.utils.EntityManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 角色服务接口实现
 * @Auther: Leo.W
 * @Date: 2019/1/9 16:52
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRespository respository;
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void add(Role role) {
        respository.save(role);
    }

    @Override
    public void update(Role role) {
        respository.save(role);
    }

    @Override
    public void delete(Integer id) {
        respository.deleteById(id);
    }

    @Override
    public List<Role> getRolesByMenuId(Integer menuId) {
        return null;
    }

    @Override
    public boolean checkRoleName(String roleName) {
        return respository.checkName(roleName)>0;
    }

    @Override
    public PaginationDataModel<Role> getPagingData(Map<String, Object> condition) {

        PaginationDataModel<Role> result = new PaginationDataModel<Role> ();

        StringBuilder countSelectSql = new StringBuilder();
        //注意：这里的SQL语句User并不是表，而是表对应的实体
        countSelectSql.append("SELECT COUNT(id) FROM Role WHERE 1=1 ");

        StringBuilder selectSql = new StringBuilder();
        selectSql.append("FROM Role WHERE 1=1 ");

        Map<String,Object> params = new HashMap<>();
        StringBuilder whereSql = new StringBuilder();
        if(condition.containsKey("roleName") && null != condition.get("roleName") && !condition.get("roleName").equals("")){
            // 注意：条件中的displayName并不是表中的字段，而是User实体的属性字段
            whereSql.append(" AND roleName=:roleName ");
            params.put("roleName",condition.get("roleName"));
        }


        String countSql = countSelectSql.append(whereSql).toString();
        Query countQuery = this.entityManager.createQuery(countSql,Long.class);
        EntityManagerHelper.setParameters(countQuery,params);
        Long count = (Long) countQuery.getSingleResult();

        String querySql = selectSql.append(whereSql).toString();
        Query query = this.entityManager.createQuery(querySql,Role.class);
        EntityManagerHelper.setParameters(query,params);

        int startIndex =0,pageSize=10;

        if(condition.containsKey("pageIndex"))
        {
            startIndex = (int)condition.get("pageIndex");

        }
        if(condition.containsKey("pageSize"))
        {
            pageSize = (int)condition.get("pageSize");
        }
        startIndex = (startIndex-1)*pageSize;

        query.setFirstResult(startIndex);
        query.setMaxResults(pageSize);

        result.setData(query.getResultList());
        result.setTotal(count);
        entityManager.close();
        return result;
    }
}
