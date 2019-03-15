package com.adwyxx.cms.services;

import com.adwyxx.cms.entities.AccessToken;
import com.adwyxx.cms.entities.User;
import com.adwyxx.cms.model.PaginationDataModel;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public interface UserService {

    User getByID(int id);

    User insert(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException;

    void Save(User user);

    User getByLogonNameAndPassword(String logonName, String password);

    void deleteById(int id);

    boolean checkLogonName(String logonName);

    PaginationDataModel<User> getPagingData(Map<String, Object> condition);

    AccessToken longOn(User user);

    User findByToken(String token);

}
