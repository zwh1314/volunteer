package com.example.volunteer.Dao;

import com.example.volunteer.Entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentDao {
    @Insert("INSERT INTO comment(comment_text, comment_publisher, comment_like, comment_date, comment_picture, time_up_2_now) " +
            "VALUES (#{commentText}, #{commentPublisher}, #{commentLike}, NOW(), #{commentPicture},#{timeUp2Now})")
    int addComment(Comment comment);

    @Update("UPDATE comment SET comment_like = #{commentLike} WHERE comment_id = #{commentId}")
    int updateCommentLike(@Param("commentLike")long commentLike, @Param("commentId")long commentId);

    @Update("UPDATE comment SET comment_text = #{commentText} WHERE comment_id = #{commentId}")
    int updateCommentText(@Param("commentText")String commentText, @Param("commentId")long commentId);

    @ResultType(Comment.class)
    @Select("SELECT comment_id as commentId, comment_text as commentText, comment_publisher as commentPublisher, " +
            "comment_like as commentLike, comment_date as commentDate ,comment_picture as commentPicture, time_up_2_now as timeUp2Now FROM comment WHERE comment_publisher " +
            "= #{commentPublisher}")
    List<Comment> findCommentById(@Param("commentPublisher")long commentPublisher);

    @Select("SELECT comment_id as commentId, comment_text as commentText, comment_publisher as commentPublisher, " +
            "comment_like as commentLike, comment_date as commentDate, comment_picture as commentPicture, time_up_2_now FROM comment WHERE TO_DAYS(NOW()) - " +
            "TO_DAYS(comment_date) <= 7")
    List<Comment> findCommentInOneWeek();

    @Select("SELECT comment_id as commentId, comment_text as commentText, comment_publisher as commentPublisher, " +
            "comment_like as commentLike, comment_date as commentDate, comment_picture as commentPicture, time_up_2_now FROM comment WHERE comment_text LIKE " +
            "CONCAT('%', #{textInclude}, '%')")
    List<Comment> findCommentByText(@Param("textInclude")String textInclude);

    @Delete("DELETE FROM comment WHERE comment_id = #{commentId}")
    int deleteCommentById(@Param("commentId")long commentId);

    @Select("SELECT comment_like as commentLike FROM comment WHERE comment_id =#{commentId}")
    Long getCommentLikeByCommentId(@Param("commentId") long commentId);

    @Select("SELECT comment_id from comment as commentId")
    List<Long> getCommentIds();
}
