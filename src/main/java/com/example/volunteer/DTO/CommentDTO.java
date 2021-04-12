package com.example.volunteer.DTO;

import com.example.volunteer.Entity.CommentPicture;
import com.example.volunteer.Entity.CommentResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@ApiModel("评论模型")
@Data
public class CommentDTO {
    @ApiModelProperty(value = "评论id")
    private long commentId;

    @ApiModelProperty(value = "评论文本")
    private String commentText;

    @ApiModelProperty(value = "评论发布者")
    private long commentPublisher;

    @ApiModelProperty(value = "评论赞数")
    private long commentLike;

    @ApiModelProperty(value = "评论发布日期")
    private Date commentDate;

    @ApiModelProperty(value = "评论图片")
    private List<CommentPicture> commentPictureList;

    @ApiModelProperty(value = "评论回复")
    private List<CommentResponse> commentResponseList;
}
