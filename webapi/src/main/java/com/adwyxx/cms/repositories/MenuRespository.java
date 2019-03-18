package com.adwyxx.cms.repositories;

import com.adwyxx.cms.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/1/11 17:30
 */
@Repository
public interface MenuRespository extends JpaRepository<Menu, Integer>, JpaSpecificationExecutor, Serializable {

    @Query(value = "SELECT DISTINCT m.* FROM role_proviliges rp " +
            " INNER JOIN menus m ON m.id=rp.menu_id " +
            " INNER JOIN roles r ON r.id=rp.role_id " +
            " WHERE EXISTS(SELECT ru.id FROM role_users ru WHERE ru.role_id=r.id AND ru.user_id=?1) " +
            " ORDER BY m.sort_no",nativeQuery = true)
    List<Menu> getByUserId(int userId);
}
