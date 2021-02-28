package com.example.volunteer.Dao;

import com.example.volunteer.Entity.ActivityUser;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface ActivityUserDao {
    @Insert("INSERT INTO activity_user(activity_id,user_id,form_status,form_date) VALUES (#{activityId},#{userId},#{formStatus},#{formDate});")
    int addActivityUser(ActivityUser activityUser);


    @Update("UPDATE activity_user SET form_status = #{formStatus} WHERE user_id = #{userId}")
    int updateFormStatusByUserId(@Param("formStatus")String formStatus, @Param("userId")long userId);

    @Update("UPDATE activity_user SET form_date = #{formDate} WHERE user_id = #{userId}")
    int updateFormDateByUserId(@Param("formDate") Date formDate, @Param("userId")long userId);

    @ResultType(ActivityUser.class)
    @Select("SELECT user_id as userId, activity_id as activityId, form_date as formDate, form_status as formStatus FROM activity_user WHERE user_id = #{userId}")
    List<ActivityUser> findActivityUserByUserId(@Param("userId")long userId);

    @ResultType(ActivityUser.class)
    @Select("SELECT user_id as userId, activity_id as activityId, form_date as formDate, form_status as formStatus FROM activity_user WHERE activity_id = #{activityId}")
    List<ActivityUser> findActivityUserByActivityId(@Param("activityId")long activityId);

    @Delete("DELETE FROM activity_user WHERE user_id = #{userId}")
    int deleteActivityUserByUserId(@Param("userId")long userId);

    @Delete("DELETE FROM activity_user WHERE activity_id = #{activityId}")
    int deleteActivityUserByActivityId(@Param("activityId")long activityId);
}