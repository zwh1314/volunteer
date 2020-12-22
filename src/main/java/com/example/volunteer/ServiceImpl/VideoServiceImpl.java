package com.example.volunteer.ServiceImpl;

import com.example.volunteer.Dao.VideoDao;
import com.example.volunteer.Entity.Video;
import com.example.volunteer.Exception.VolunteerRuntimeException;
import com.example.volunteer.Request.VideoRequest;
import com.example.volunteer.Service.VideoService;
import com.example.volunteer.enums.ResponseEnum;
import com.example.volunteer.utils.SerialUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    private static final Logger logger = LoggerFactory.getLogger(VideoServiceImpl.class);

    @Autowired
    private VideoDao videoDao;

    @Override
    public boolean addVideo(VideoRequest videoRequest){
        boolean result;

        for(Video video:videoRequest.getVideoList()) {
            result = videoDao.addVideo(video) > 0;
            if (!result) {
                logger.error("[addVideo Fail], request: {}", SerialUtil.toJsonStr(videoRequest));
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateVideoTextContent(String textContent, long videoId){
        boolean result;
        result = videoDao.updateVideoText(textContent,videoId) > 0;
        if (!result) {
            logger.error("[updateVideoText Fail], videoId: {}", SerialUtil.toJsonStr(videoId));
        }
        return result;
    }

    @Override
    public boolean updateVideoLikeNumber(long likeNumber, long videoId){
        boolean result;
        result = videoDao.updateVideoLike(likeNumber,videoId) > 0;
        if (!result) {
            logger.error("[updateVideoLike Fail], videoId: {}", SerialUtil.toJsonStr(videoId));
        }
        return result;
    }

    @Override
    public List<Video> getVideoByPublisherId(long publisherId){
        List<Video> videoList = videoDao.findVideoByUser(publisherId);
        if (videoList == null) {
            throw new VolunteerRuntimeException(ResponseEnum.OBJECT_PUBLISHER_NOT_FOUND);
        }
        return videoList;
    }

    @Override
    public List<Video> getVideoByRelativeText(String relativeText){
        List<Video> videoList = videoDao.findVideoByText(relativeText);
        if (videoList == null) {
            throw new VolunteerRuntimeException(ResponseEnum.OBJECT_RELATIVE_TEXT_NOT_FOUND);
        }
        return videoList;
    }

    @Override
    public List<Video> getVideoInOneWeek(){
        List<Video> videoList = videoDao.findVideoInOneWeek();
        if (videoList == null) {
            throw new VolunteerRuntimeException(ResponseEnum.OBJECT_IN_ONE_WEEK_NOT_FOUND);
        }
        return videoList;
    }

    @Override
    public boolean deleteVideoById(long videoId){
        boolean result;

        result=videoDao.deleteVideoById(videoId) > 0;
        if(!result){
            logger.error("[deleteVideoById Fail], videoId: {}", SerialUtil.toJsonStr(videoId));
        }
        return result;
    }
}
