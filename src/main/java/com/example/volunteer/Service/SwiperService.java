package com.example.volunteer.Service;

import com.example.volunteer.Entity.Swiper;
import com.example.volunteer.Response.Response;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SwiperService {
    Response<Boolean> addSwiper(Swiper swiper, MultipartFile uploadFile);

    Response<Boolean> updateSwiperPriority(String newSwiperPriority, long swiperId);

    Response<Boolean> updateSwiperText(String newSwiperText, long swiperId);

    Response<List<Swiper>> getSwiperByNewsId(long newsId);

    Response<List<Swiper>> getSwiperByPriority(String priority);

    Response<Boolean> deleteSwiperById(long swiperId);
}
