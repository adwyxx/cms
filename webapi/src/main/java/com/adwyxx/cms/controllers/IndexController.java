package com.adwyxx.cms.controllers;

import com.adwyxx.cms.entities.CarouselItem;
import com.adwyxx.cms.services.CarouselItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/21 15:19
 */
@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private CarouselItemService carouselItemService;

    @GetMapping("/carousel/{topNum}")
    public List<CarouselItem> getTopItmes(@PathVariable("topNum") int topNum)
    {
        return carouselItemService.getTopItmes(topNum);
    }
}
