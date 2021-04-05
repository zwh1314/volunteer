package com.example.volunteer.Service.ServiceImpl;

import com.example.volunteer.Dao.VideoDao;
import com.example.volunteer.Entity.Video;
import com.example.volunteer.Response.Response;
import com.example.volunteer.Service.VideoService;
import com.example.volunteer.enums.ResponseEnum;
import com.example.volunteer.utils.OSSUtil;
import com.example.volunteer.utils.SerialUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    private static final Logger logger = LoggerFactory.getLogger(VideoServiceImpl.class);

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private OSSUtil ossUtil;

    @Override
    public Response<Boolean> addVideo(Video video, MultipartFile video_mp4){
        Response<Boolean> response=new Response<>();

        String bucketName = "videos-url";
        String filename = "videoPublisher_"+video.getVideoPublisher()+"/"+video_mp4.getOriginalFilename();
        String url = ossUtil.uploadFile(bucketName,video_mp4,filename);
        if(StringUtils.isBlank(url)){
            logger.error("[addVideo Fail], video_mp4: {}", SerialUtil.toJsonStr(video_mp4.getOriginalFilename()));
            response.setFail(ResponseEnum.UPLOAD_OSS_FAILURE);
            return response;
        }
        video.setVideoUrl(url);

        boolean result = videoDao.addVideo(video) > 0;
        if (!result) {
            logger.error("[addVideo Fail], video_mp4: {}", SerialUtil.toJsonStr(video_mp4));
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else {
            response.setSuc(true);
        }
        return response;
    }

    @Override
    public Response<Boolean> updateVideoTextContent(String textContent, long videoId){
        Response<Boolean> response=new Response<>();

        boolean result = videoDao.updateVideoText(textContent,videoId) > 0;
        if (!result) {
            logger.error("[updateVideoText Fail], videoId: {}", SerialUtil.toJsonStr(videoId));
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else{
            response.setSuc(true);
        }
        return response;
    }

    @Override
    public Response<Boolean> updateVideoLikeNumber(long likeNumber, long videoId){
        Response<Boolean> response=new Response<>();

        boolean result = videoDao.updateVideoLike(likeNumber,videoId) > 0;
        if (!result) {
            logger.error("[updateVideoLike Fail], videoId: {}", SerialUtil.toJsonStr(videoId));
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else {
            response.setSuc(true);
        }
        return response;
    }

    @Override
    public Response<List<Video>> getVideoByPublisherId(long publisherId){
        Response<List<Video>> response=new Response<>();

        List<Video> videoList = videoDao.findVideoByUser(publisherId);
        if (videoList.size() == 0) {
            response.setFail(ResponseEnum.OBJECT_PUBLISHER_NOT_FOUND);
        }
        else {
            response.setSuc(videoList);
        }
        return response;
    }

    @Override
    public Response<List<Video>> getVideoByRelativeText(String relativeText){
        Response<List<Video>> response=new Response<>();

        List<Video> videoList = videoDao.findVideoByText(relativeText);
        if (videoList.size() == 0) {
            response.setFail(ResponseEnum.OBJECT_RELATIVE_TEXT_NOT_FOUND);
        }
        else {
            response.setSuc(videoList);
        }
        return response;
    }

    @Override
    public Response<List<Video>> getVideoInOneWeek(){
        Response<List<Video>> response=new Response<>();

        List<Video> videoList = videoDao.findVideoInOneWeek();
        if (videoList.size() == 0) {
            response.setFail(ResponseEnum.OBJECT_IN_ONE_WEEK_NOT_FOUND);
        }
        else {
            response.setSuc(videoList);
        }
        return response;
    }

    @Override
    public Response<Boolean> deleteVideoById(long videoId){
        Response<Boolean> response=new Response<>();

        boolean result=videoDao.deleteVideoById(videoId) > 0;
        if(!result){
            logger.error("[deleteVideoById Fail], videoId: {}", SerialUtil.toJsonStr(videoId));
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else{
            response.setSuc(true);
        }
        return response;
    }
    @Override
    public Response<List<Video>> getVideoByNumber(long number){
        Response<List<Video>> response=new Response<>();

        List<Video> videoList = videoDao.findVideoByNumber(number);
        if (videoList.size() == 0) {
            response.setFail(ResponseEnum.OBJECT_RELATIVE_TEXT_NOT_FOUND);
        }
        else {
            response.setSuc(videoList);
        }
        return response;
    }

}
