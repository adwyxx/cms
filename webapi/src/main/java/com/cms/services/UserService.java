package com.cms.services;

import com.cms.entities.User;

public interface UserService {

    public User getByID(Integer id);

    public User insert(User user);

    public void Save(User user);

    public User getByLogonNameAndPassword(String logonName,String password);

    public void deleteById(Integer id);

    public boolean checkLogonName(String logonName);
}
