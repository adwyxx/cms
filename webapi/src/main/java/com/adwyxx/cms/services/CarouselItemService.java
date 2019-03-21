package com.adwyxx.cms.services;

import com.adwyxx.cms.entities.CarouselItem;

import java.util.List;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/21 15:09
 */
public interface CarouselItemService {

    List<CarouselItem> getTopItmes(int topNum);

    CarouselItem add(CarouselItem item);

    void update(CarouselItem item);

    void deleteById(int id);
}
