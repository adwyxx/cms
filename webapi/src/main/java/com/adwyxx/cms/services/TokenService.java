package com.adwyxx.cms.services;

import com.adwyxx.cms.entities.AccessToken;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/14 15:34
 */
public interface TokenService {

    AccessToken getTokenByUserId(int userId);

    AccessToken generateAcceseToken(int userId,String clientId);

    void deleteById(String id);
}
