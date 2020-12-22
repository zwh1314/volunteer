package com.example.volunteer.Controller;

import com.example.volunteer.Service.VideoCommentService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "视频评论Controller")
@RestController
@RequestMapping("videoComment")
public class VideoCommentController {
    private static final Logger logger = LoggerFactory.getLogger(VideoCommentController.class);

    @Autowired
    private VideoCommentService videoCommentService;
}
