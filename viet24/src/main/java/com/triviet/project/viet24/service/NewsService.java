package com.triviet.project.viet24.service;

import com.triviet.project.viet24.base.entity.BaseResponse;
import com.triviet.project.viet24.entity.News;
import com.triviet.project.viet24.implement.INewsService;
import com.triviet.project.viet24.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NewsService implements INewsService {
    @Autowired
    private NewsRepository newsRepository;

    @Override
    public BaseResponse<News> getAllNews(int page) {
        Page<News> listNews =  newsRepository.getAllNews( PageRequest.of(page, 7));
        return new BaseResponse<News>(200,"success",listNews.getContent().get(0));
    }
}
