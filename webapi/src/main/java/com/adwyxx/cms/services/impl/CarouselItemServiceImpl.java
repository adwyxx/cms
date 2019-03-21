package com.adwyxx.cms.services.impl;

import com.adwyxx.cms.entities.CarouselItem;
import com.adwyxx.cms.repositories.CarouselItemRepository;
import com.adwyxx.cms.services.CarouselItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/21 15:12
 */
@Service
public class CarouselItemServiceImpl implements CarouselItemService {

    @Autowired
    private CarouselItemRepository repository;

    @Override
    public List<CarouselItem> getTopItmes(int topNum) {
        return repository.getTopItmes(topNum);
    }

    @Override
    public CarouselItem add(CarouselItem item) {
        return repository.save(item);
    }

    @Override
    public void update(CarouselItem item) {
        repository.saveAndFlush(item);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
