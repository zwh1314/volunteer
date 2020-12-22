package com.example.volunteer.Controller;

import com.example.volunteer.Service.CommentResponseService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "评论回复Controller")
@RestController
@RequestMapping("commentResponse")
public class CommentResponseController {
    private static final Logger logger = LoggerFactory.getLogger(CommentResponseController.class);

    @Autowired
    private CommentResponseService responseService;
}
