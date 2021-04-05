package com.example.volunteer.Entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name = "comment")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    @ApiModelProperty(value = "评论id")
    private long commentId;

    @Column(name = "comment_text")
    @ApiModelProperty(value = "评论文本")
    private String commentText;

    @Column(name = "comment_publisher")
    @ApiModelProperty(value = "评论发布者")
    private long commentPublisher;

    @Column(name = "comment_like")
    @ApiModelProperty(value = "评论赞数")
    private long commentLike;

    @Column(name = "comment_date")
    @ApiModelProperty(value = "评论发布日期")
    private Date commentDate;

    @Column(name = "time_up_2_now")
    @ApiModelProperty("距今发布时间")
    private String timeUp2Now;

    @Column(name = "is_comment_picture",nullable = false)
    @ApiModelProperty(value = "是否已上传报名表")
    private boolean is_comment_picture;
}
