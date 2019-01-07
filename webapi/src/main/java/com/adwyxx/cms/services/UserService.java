package com.adwyxx.cms.services;

import com.adwyxx.cms.entities.User;
import com.adwyxx.cms.model.PaginationDataModel;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public interface UserService {

    public User getByID(Integer id);

    public User insert(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException;

    public void Save(User user);

    public User getByLogonNameAndPassword(String logonName,String password);

    public void deleteById(Integer id);

    public boolean checkLogonName(String logonName);

    public PaginationDataModel<User> getPagingData(Map<String,Object> condition);

}
