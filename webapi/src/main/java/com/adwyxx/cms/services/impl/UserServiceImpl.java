package com.adwyxx.cms.services.impl;

import com.adwyxx.cms.entities.User;
import com.adwyxx.cms.model.PaginationDataModel;
import com.adwyxx.cms.repositories.UserRepository;
import com.adwyxx.cms.services.UserService;
import com.adwyxx.cms.utils.EntityManagerHelper;
import com.adwyxx.cms.utils.Md5Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getByID(Integer id) {
        return userRepository.getOne(id);
    }

    @Override
    public User insert(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
            user.setPassword(Md5Helper.EncoderByMd5("pass@123"));
        return userRepository.save(user);
    }

    @Override
    public void Save(User user) {
        userRepository.updateUser(user.getId(),user.getDisplayName(),user.getLogonName(),user.getEmail(),user.getMobile());
    }

    @Override
    public User getByLogonNameAndPassword(String logonName, String password) {
        List<User> list = userRepository.getByLogonNameAndPassword(logonName,password);
        if(list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean checkLogonName(String logonName) {
        Integer count = userRepository.checkLogonName(logonName);
        return count>0;
    }

    @Override
    public PaginationDataModel<User> getPagingData(Map<String, Object> condition) {

        PaginationDataModel<User> result = new PaginationDataModel<User> ();

        StringBuilder countSelectSql = new StringBuilder();
        //注意：这里的SQL语句User并不是表，而是表对应的实体
        countSelectSql.append("SELECT COUNT(id) FROM User WHERE 1=1 ");

        StringBuilder selectSql = new StringBuilder();
        selectSql.append("FROM User WHERE 1=1 ");

        Map<String,Object> params = new HashMap<>();
        StringBuilder whereSql = new StringBuilder();
        if(condition.containsKey("displayName") && null != condition.get("displayName") && !condition.get("displayName").equals("")){
            // 注意：条件中的displayName并不是表中的字段，而是User实体的属性字段
            whereSql.append(" AND displayName=:displayName ");
            params.put("displayName",condition.get("displayName"));
        }
        if(condition.containsKey("logonName") && null != condition.get("logonName")&& !condition.get("logonName").equals("")){
            whereSql.append(" AND logonName=:logonName ");
            params.put("logonName",condition.get("logonName"));
        }

        String countSql = countSelectSql.append(whereSql).toString();
        Query countQuery = this.entityManager.createQuery(countSql,Long.class);
        EntityManagerHelper.setParameters(countQuery,params);
        Long count = (Long) countQuery.getSingleResult();

        String querySql = selectSql.append(whereSql).toString();
        Query query = this.entityManager.createQuery(querySql,User.class);
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
