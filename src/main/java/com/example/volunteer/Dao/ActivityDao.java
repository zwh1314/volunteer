package com.example.volunteer.Dao;

import com.example.volunteer.DTO.ActivityDTO;
import com.example.volunteer.Entity.Activity;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public  interface ActivityDao {
    @Insert("INSERT INTO activity(activity_name,activity_content,activity_organizer,enrolled_number,requested_number,activity_type,activity_date) " +
            "VALUES (#{activityName},#{activityContent},#{activityOrganizer},#{enrolledNumber},#{requestNumber}, #{activityType},#{activityDate});")
    int insertActivity(Activity activity);

    @ResultType(ActivityDTO.class)
    @Select("SELECT activity_id as activityId,activity_name as activityName,activity_content as activityContent,activity_organizer as activityOrganizer, " +
            "enrolled_number as enrolledNumber, requested_number as requestedNumber, activity_type as activityType, activity_date as activityDate FROM activity " +
            "WHERE activity_id = #{activityId}")
    ActivityDTO getActivityByActivityId(@Param("activityId") long activityId);

    @ResultType(ActivityDTO.class)
    @Select("SELECT activity_id as activityId,activity_name as activityName,activity_content as activityContent,activity_organizer as activityOrganizer, enrolled_number as enrolledNumber, requested_number as requestNumber, activity_type as activityType,activity_date as activityDate FROM activity WHERE activity_name = #{activityName}")
    ActivityDTO getActivityByActivityName(@Param("activityName") String activityName);

    @ResultType(ActivityDTO.class)
    @Select("SELECT activity_id as activityId,activity_name as activityName,activity_content as activityContent,activity_organizer as activityOrganizer, enrolled_number as enrolledNumber, requested_number as requestNumber, activity_type as activityType,activity_date as activityDate FROM activity WHERE activity_organizer = #{activityOrganizer}")
    ActivityDTO getActivityByOrganizer(@Param("activityOrganizer") String activityOrganizer);

    @Update("UPDATE activity SET activity_name = #{activityName},activity_organizer = #{activityOrganizer}, activity_content = #{activityContent},enrolled_number = #{enrolledNumber}, requested_number = #{requestedNumber},activity_type = #{activityType},activity_date = #{activityDate} WHERE activity_id = #{activityId}")
    int updateActivity(Activity activity);

    @Delete("Delete From activity WHERE activity_id=#{activityId}")
    int deleteActivityByActivityId(@Param("activityId")long activityId);
}
