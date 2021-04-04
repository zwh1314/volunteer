package com.example.volunteer.Dao;

import com.example.volunteer.DTO.UserInfoDTO;
import com.example.volunteer.Entity.UserInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserInfoDao {

    @ResultType(UserInfoDTO.class)
    @Select("SELECT user_id as userId, user_name as userName, priority, head_picture as headPicture, " +
            "address, fax, tel, qq, mail_address as mailAddress, introduction, credits FROM user_info WHERE id = #{id}")
    UserInfoDTO getUserInfoById(@Param("id")int id);

    @ResultType(UserInfoDTO.class)
    @Select("SELECT user_id as userId, user_name as userName, head_picture,birthday,address,major,introduction,credits " +
            "FROM user_info WHERE user_id = #{userId}")
    UserInfoDTO getUserInfoByUserId(@Param("userId") long userId);

    @Insert("INSERT INTO user_info(user_name, priority, head_picture, address, fax, " +
            "tel, qq, mail_address, introduction,credits) " +
            "VALUES(#{userName}, #{priority}, #{headPicture}, #{address}, " +
            "#{fax}, #{tel}, #{qq}, #{mailAddress}, #{introduction}, #{credits})")
    int addUserInfo(UserInfo userInfo);

    @Update("UPDATE user_info SET user_name = #{userName}, address = #{address}, fax = #{fax}, tel = #{tel}, " +
            "qq = #{qq}, mail_address = #{mailAddress}, introduction = #{introduction}, credits = #{credits} " +
            "WHERE user_id = #{userId}")
    int updateUserInfo(UserInfo userInfo);

    @Update("UPDATE user_info SET priority = #{priority} WHERE user_id = #{userId}")
    int updatePriority(@Param("userId") String userId, @Param("priority") String priority);

    @Update("UPDATE user_info SET head_picture = #{headPicture} WHERE user_id = #{userId}")
    int updateHeadPicture(@Param("userId") long userId, @Param("headPicture") String headPicture);

    @Delete("DELETE FROM user_info WHERE user_id = #{userId}")
    int deleteUserInfoByUserId(@Param("userId")long userId);

    @ResultType(UserInfoDTO.class)
    @Select("SELECT credits, user_id as userId FROM user_info WHERE user_id = #{userId}")
    UserInfoDTO getCreditsById(@Param("userId")long userId);
}
