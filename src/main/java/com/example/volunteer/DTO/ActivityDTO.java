package com.example.volunteer.DTO;

import com.example.volunteer.Entity.ActivityPicture;
import com.example.volunteer.Entity.ActivitySignFileModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @param: none
 * @description: 返回给前端的活动信息实体类
 *
 **/
@ApiModel("活动模型")
@Data
public class ActivityDTO{

    @ApiModelProperty(value = "活动id")
    private long activityId;

    @ApiModelProperty(value = "活动名称")
    private String activityName;

    @ApiModelProperty(value = "活动内容")
    private String activityContent;

    @ApiModelProperty(value = "活动组织者")
    private String activityOrganizer;

    @ApiModelProperty(value = "活动时间")
    private Date activityDate;

    @ApiModelProperty(value = "活动图片")
    private List<ActivityPicture> activityPictureList;

    @ApiModelProperty(value = "活动报名表模板")
    private List<ActivitySignFileModel> activitySignFileModelList;
}