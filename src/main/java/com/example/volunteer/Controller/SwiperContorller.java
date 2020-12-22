package com.example.volunteer.Controller;

import com.example.volunteer.Service.SwiperService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "轮播图Controller")
@RestController
@RequestMapping("swiper")
public class SwiperContorller extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(SwiperContorller.class);

    @Autowired
    private SwiperService swiperService;
}
