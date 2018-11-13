package com.cms.services.impl;

import com.cms.entities.User;
import com.cms.repositories.UserRepository;
import com.cms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getByID(Integer id) {
        return userRepository.getOne(id);
    }

    @Override
    public User insert(User user) {
        return userRepository.save(user);
    }

    @Override
    public void Save(User user) {
        userRepository.save(user);
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

}
