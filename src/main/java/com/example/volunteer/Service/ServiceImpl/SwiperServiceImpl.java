package com.example.volunteer.Service.ServiceImpl;

import com.example.volunteer.Dao.SwiperDao;
import com.example.volunteer.Entity.Swiper;
import com.example.volunteer.Exception.VolunteerRuntimeException;
import com.example.volunteer.Response.Response;
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
    public Response<Boolean> addSwiper(Swiper swiper){
        Response<Boolean> response=new Response<>();
        boolean result = swiperDao.addSwiper(swiper) > 0;
        if (!result) {
            logger.error("[addSwiper Fail], swiper: {}", SerialUtil.toJsonStr(swiper));
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else {
            response.setSuc(true);
        }
        return response;
    }

    @Override
    public Response<Boolean> updateSwiperPriority(String newSwiperPriority, long swiperId){
        Response<Boolean> response=new Response<>();
        boolean result = swiperDao.updateSwiperPriority(newSwiperPriority,swiperId) > 0;
        if (!result) {
            logger.error("[updateSwiperPriority Fail], swiperId: {}", SerialUtil.toJsonStr(swiperId));
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else {
            response.setSuc(true);
        }
        return response;
    }

    @Override
    public Response<Boolean> updateSwiperText(String newSwiperText, long swiperId){
        Response<Boolean> response=new Response<>();
        boolean result = swiperDao.updateSwiperText(newSwiperText,swiperId) > 0;
        if (!result) {
            logger.error("[updateSwiperText Fail], swiperId: {}", SerialUtil.toJsonStr(swiperId));
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else {
            response.setSuc(true);
        }
        return response;
    }

    @Override
    public Response<List<Swiper>> getSwiperByNewsId(long newsId){
        Response<List<Swiper>> response=new Response<>();

        List<Swiper> swiperList = swiperDao.findSwiperByNews(newsId);
        if (swiperList.size() == 0) {
            response.setFail(ResponseEnum.SWIPER_NEWS_NOT_FOUND);
        }
        else {
            response.setSuc(swiperList);
        }
        return response;
    }

    @Override
    public Response<List<Swiper>> getSwiperByPriority(String priority){
        Response<List<Swiper>> response=new Response<>();

        List<Swiper> swiperList = swiperDao.findSwiperByPriority(priority);
        if (swiperList == null) {
            response.setFail(ResponseEnum.SWIPER_PRIORITY_NOT_FOUND);
        }
        else {
            response.setSuc(swiperList);
        }
        return response;
    }

    @Override
    public Response<Boolean> deleteSwiperById(long swiperId){
        Response<Boolean> response=new Response<>();

        boolean result=swiperDao.deleteSwiperById(swiperId) > 0;
        if(!result){
            logger.error("[deleteSwiperById Fail], swiperId: {}", SerialUtil.toJsonStr(swiperId));
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else{
            response.setSuc(true);
        }
        return response;
    }
}
