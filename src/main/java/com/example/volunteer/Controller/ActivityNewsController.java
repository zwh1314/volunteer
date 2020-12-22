package com.example.volunteer.Controller;

import com.example.volunteer.Service.ActivityNewsService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "活动新闻Controller")
@RestController
@RequestMapping("activityNews")
public class ActivityNewsController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(ActivityNewsController.class);

    @Autowired
    private ActivityNewsService activityNewsService;
}
