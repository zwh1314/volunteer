package com.example.volunteer.Dao;
import com.example.volunteer.Entity.User;
import org.apache.ibatis.annotations.*;


@Mapper
public  interface UserDao {
    @Insert("INSERT INTO user(id,username,password,email,headpicturestr,usersentence) VALUES (#{id},#{Username},#{Password},#{Email},#{HeadPictureStr},#{UserSentence});")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(User user);

    @ResultType(User.class)
    @Select("SELECT * FROM user WHERE id = #{id}")
    User getUserById(@Param("id") int id);

    @Update("UPDATE user set password=#{Password},email=#{Email},headpicturestr=#{HeadPictureStr},usersentence=#{UserSentence} WHERE id=#{id}")
    int update(User user);

    @Delete("Delete From user WHERE id=#{id}")
    public int deleteById(@Param("id")int id);
}
