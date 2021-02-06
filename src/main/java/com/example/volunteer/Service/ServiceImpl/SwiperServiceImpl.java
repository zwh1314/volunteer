package com.example.volunteer.Service.ServiceImpl;

import com.example.volunteer.Dao.SwiperDao;
import com.example.volunteer.Entity.Swiper;
import com.example.volunteer.Exception.VolunteerRuntimeException;
import com.example.volunteer.Service.SwiperService;
import com.example.volunteer.enums.ResponseEnum;
import com.example.volunteer.utils.SerialUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SwiperServiceImpl implements SwiperService {
    private static final Logger logger = LoggerFactory.getLogger(SwiperServiceImpl.class);

    @Autowired
    private SwiperDao swiperDao;

    @Override
    public boolean addSwiper(Swiper swiper){
        boolean result;
        result = swiperDao.addSwiper(swiper) > 0;
            if (!result) {
                logger.error("[addSwiper Fail], swiper: {}", SerialUtil.toJsonStr(swiper));
            }
        return result;
    }

    @Override
    public boolean updateSwiperPriority(String newSwiperPriority, long swiperId){
        boolean result;
        result = swiperDao.updateSwiperPriority(newSwiperPriority,swiperId) > 0;
        if (!result) {
            logger.error("[updateSwiperPriority Fail], swiperId: {}", SerialUtil.toJsonStr(swiperId));
        }
        return result;
    }

    @Override
    public boolean updateSwiperText(String newSwiperText, long swiperId){
        boolean result;
        result = swiperDao.updateSwiperText(newSwiperText,swiperId) > 0;
        if (!result) {
            logger.error("[updateSwiperText Fail], swiperId: {}", SerialUtil.toJsonStr(swiperId));
        }
        return result;
    }

    @Override
    public List<Swiper> getSwiperByNewsId(long newsId){
        List<Swiper> swiperList = swiperDao.findSwiperByNews(newsId);
        if (swiperList == null) {
            throw new VolunteerRuntimeException(ResponseEnum.SWIPER_NEWS_NOT_FOUND);
        }
        return swiperList;
    }

    @Override
    public List<Swiper> getSwiperByPriority(String priority){
        List<Swiper> swiperList = swiperDao.findSwiperByPriority(priority);
        if (swiperList == null) {
            throw new VolunteerRuntimeException(ResponseEnum.SWIPER_PRIORITY_NOT_FOUND);
        }
        return swiperList;
    }

    @Override
    public boolean deleteSwiperById(long swiperId){
        boolean result;

        result=swiperDao.deleteSwiperById(swiperId) > 0;
        if(!result){
            logger.error("[deleteSwiperById Fail], swiperId: {}", SerialUtil.toJsonStr(swiperId));
        }
        return result;
    }
}
