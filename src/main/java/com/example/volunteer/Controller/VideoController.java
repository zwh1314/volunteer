package com.example.volunteer.Controller;

import com.example.volunteer.Service.VideoService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "视频Controller")
@RestController
@RequestMapping("video")
public class VideoController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(VideoController.class);

    @Autowired
    private VideoService videoService;

}
