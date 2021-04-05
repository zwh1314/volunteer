package com.example.volunteer.Dao;

import com.example.volunteer.Entity.ActivitySignFile;
import com.example.volunteer.Entity.CommentPicture;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface CommentPictureDao {
    @Insert("INSERT INTO comment_picture(comment_id,picture_id,file_name,file_url) VALUES (#{commentId},#{pictureId},#{fileName},#{fileUrl});")
    int addCommentPicture(CommentPicture commentPicture);

    @ResultType(CommentPicture.class)
    @Select("SELECT comment_id as commentId,picture_id as pictureId, file_name as fileName,file_url as fileUrl FROM comment_picture WHERE comment_id = #{commentId}")
    List<CommentPicture> getCommentPictureByCommentId(@Param("commentId") long commentId);

    @Update("UPDATE comment_picture SET file_name = #{fileName},file_url = #{fileUrl} WHERE comment_id = #{commentId} AND picture_id = #{pictureId}")
    int updateCommentPicture(CommentPicture commentPicture);

    @Delete("Delete From comment_picture WHERE comment_id=#{commentId}")
    int deleteCommentPictureByCommentId(@Param("commentId")long commentId);


}
