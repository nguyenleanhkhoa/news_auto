package com.triviet.project.viet24.implement;

import com.triviet.project.viet24.base.entity.BaseResponse;
import com.triviet.project.viet24.entity.News;
import org.springframework.data.domain.Pageable;

public interface INewsService {
	BaseResponse<News> getAllNews(int page);
}
