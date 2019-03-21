package com.adwyxx.cms.repositories;

import com.adwyxx.cms.entities.CarouselItem;
import com.adwyxx.cms.entities.RoleProviliges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/1/11 17:32
 */
@Repository
public interface CarouselItemRepository extends JpaRepository<CarouselItem, Integer>, JpaSpecificationExecutor, Serializable {

    @Query(value = "SELECT * FROM carousel_item WHERE enabled=1 ORDER BY sort_no LIMIT 0,?1", nativeQuery = true)
    List<CarouselItem> getTopItmes(int topNum);
}
