package com.example.volunteer.Dao;
import com.example.volunteer.DTO.UserDTO;
import com.example.volunteer.Entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public  interface UserDao {
    @Insert("INSERT INTO user(user_name,password,tel,priority,mail_address) VALUES (#{userName},#{password},#{tel},#{priority},#{mailAddress});")
    int insertUser(User user);

    @ResultType(UserDTO.class)
    @Select("SELECT user_id as userId,user_name as userName,tel,priority,mail_address as mailAddress FROM user WHERE user_id = #{userId}")
    UserDTO getUserByUserId(@Param("userId") long userId);

    @ResultType(UserDTO.class)
    @Select("SELECT user_id as userId,user_name as userName,tel,priority,mail_address as mailAddress FROM user WHERE tel = #{tel} and password = #{password}")
    UserDTO getUserByTelAndPassword(@Param("tel") String tel,@Param("password") String password);

    @ResultType(UserDTO.class)
    @Select("SELECT user_id as userId, user_name as userName, tel,priority,mail_address as mailAddress FROM user WHERE mail_address = #{mailAddress} and password = #{password}")
    UserDTO getUserByMailAndPassword(@Param("mailAddress") String mailAddress, @Param("password") String password);

    @ResultType(UserDTO.class)
    @Select("SELECT user_id as userId,user_name as userName,tel,priority,mail_address as mailAddress FROM user WHERE tel = #{tel}")
    UserDTO getUserByTel(@Param("tel") String tel);

    @ResultType(UserDTO.class)
    @Select("SELECT user_id as userId,user_name as userName,tel,priority,mail_address as mailAddress FROM user WHERE mail_address = #{mailAddress}")
    UserDTO getUserByMail(String mailAddress);

    @Update("UPDATE user SET password = #{newPassword} WHERE tel = #{tel}")
    int updatePassword(@Param("tel") String tel, @Param("newPassword") String newPassword);

    @Update("UPDATE user SET password = #{newPassword} WHERE mail_address = #{mailAddress} ")
    int updatePasswordByMail(@Param("mailAddress") String mail,@Param("newPassword") String newPassword);

    @Delete("Delete From user WHERE user_id=#{userid}")
    int deleteByUserId(@Param("userid")long userid);



}
