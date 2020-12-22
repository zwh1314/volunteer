package com.example.volunteer.Dao;

import com.example.volunteer.Entity.Video;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VideoDao {
    @Insert("INSERT INTO video(video_url, video_text, video_publisher, video_like, video_date) " +
            "VALUES(#{videoUrl}, #{videoText}, #{videoPublisher}, #{videoLike}, NOW())")
    int addVideo(Video video);

    @Update("UPDATE video SET video_text = #{videoText} WHERE video_id = #{videoId}")
    int updateVideoText(@Param("videoText")String videoText, @Param("videoId")long videoId);

    @Update("UPDATE video SET video_like = #{videoLike} WHERE video_id = #{videoId}")
    int updateVideoLike(@Param("videoLike")long videoLike, @Param("videoId")long videoId);

    @ResultType(Video.class)
    @Select("SELECT video_id as videoId, video_url as videoUrl, video_text as videoText, " +
            "video_publisher as videoPublisher, video_like as videoLike, video_date as videoDate " +
            "FROM video WHERE video_publisher = #{userId}")
    List<Video> findVideoByUser(@Param("userId")long userId);

    @ResultType(Video.class)
    @Select("SELECT video_id as videoId, video_url as videoUrl, video_text as videoText, " +
            "video_publisher as videoPublisher, video_like as videoLike, video_date as videoDate " +
            "FROM video WHERE video_text LIKE CONCAT('%',#{textInclude},'%')")
    List<Video> findVideoByText(@Param("textInclude")String textInclude);

    @ResultType(Video.class)
    @Select("SELECT video_id as videoId, video_url as videoUrl, video_text as videoText, " +
            "video_publisher as videoPublisher, video_like as videoLike, video_date as videoDate " +
            "FROM video WHERE TO_DAYS(NOW()) - TO_DAYS(video_date) <= 7")
    List<Video> findVideoInOneWeek();

    @Delete("DELETE FROM video WHERE video_id = #{videoId}")
    int deleteVideoById(@Param("videoId")long videoId);
}
