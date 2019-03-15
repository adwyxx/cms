package com.adwyxx.cms.repositories;

import com.adwyxx.cms.entities.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/14 15:30
 */
@Repository
public interface AccessTokenRespository  extends JpaRepository<AccessToken, String>, JpaSpecificationExecutor, Serializable {

    @Query(value = "SELECT TOP 1 * FROM access_token WHERE user_id=?1", nativeQuery = true)
    public AccessToken getTokenByUserId(int userId);
}
