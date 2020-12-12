package com.example.volunteer.Dao;
import com.example.volunteer.Entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public  interface UserDao {
    @Insert("INSERT INTO user(userid,username,password,email,headpicturestr,usersentence) VALUES (#{UserId},#{Username},#{Password},#{Email},#{HeadPictureStr},#{UserSentence});")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(User user);

    @ResultType(User.class)
    @Select("SELECT * FROM user WHERE userid = #{userid}")
    List<User> getUserByUserId(@Param("userid") int userid);

    @Update("UPDATE user set password=#{Password},email=#{Email},headpicturestr=#{HeadPictureStr},usersentence=#{UserSentence} WHERE userid=#{UserId}")
    int update(User user);

    @Delete("Delete From user WHERE userid=#{userid}")
    public int deleteByUserId(@Param("userid")int userid);
}
