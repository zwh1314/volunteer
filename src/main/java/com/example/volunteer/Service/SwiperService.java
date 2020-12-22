package com.example.volunteer.Service;

import com.example.volunteer.Entity.Swiper;

import java.util.List;

public interface SwiperService {
    boolean addSwiper(Swiper swiper);

    boolean updateSwiperPriority(String newSwiperPriority, long swiperId);

    boolean updateSwiperText(String newSwiperText, long swiperId);

    List<Swiper> getSwiperByNewsId(long newsId);

    List<Swiper> getSwiperByPriority(String priority);

    boolean deleteSwiperById(long swiperId);
}
