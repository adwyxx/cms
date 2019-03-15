package com.adwyxx.cms.services.impl;

import com.adwyxx.cms.entities.AccessToken;
import com.adwyxx.cms.repositories.AccessTokenRespository;
import com.adwyxx.cms.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/14 15:42
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private AccessTokenRespository tokenRespository;

    @Override
    public AccessToken getTokenByUserId(int userId) {
        return tokenRespository.getTokenByUserId(userId);
    }

    /**
    * @description : 生成Token
    * @param userId : 用户ID
    * @param clientId : 客户端应用ID
     * @author : Leo.W
    * @date : 2019/3/14 16:01
    * @return : Token信息
    **/
    @Override
    public AccessToken generateAcceseToken(int userId,String clientId) {
        AccessToken token = new AccessToken(userId,clientId);
        tokenRespository.save(token);
        return token;
    }

    @Override
    public void deleteById(String id){
        tokenRespository.deleteById(id);
    }
}
