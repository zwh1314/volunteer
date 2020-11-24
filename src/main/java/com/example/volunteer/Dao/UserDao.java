package com.example.volunteer.Dao;
import com.example.volunteer.Entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {
    @Insert("INSERT INTO user(id,username,password,email,headpicturestr,usersentence) VALUES (#{id},#{username},#{password},#{email},#{headpicturestr},#{usersentence});")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(User user);

    @ResultType(User.class)
    @Select("SELECT * FROM user WHERE id = #{id}")
    User getUserById(@Param("id") int id);
}
