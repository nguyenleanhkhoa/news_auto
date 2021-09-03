package com.triviet.project.viet24.implement;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.triviet.project.viet24.base.entity.BaseResponse;
import com.triviet.project.viet24.entity.WebSource;
import org.springframework.data.domain.Page;

public interface IWebsiteService {
    BaseResponse<Page<WebSource>> findAll(int page);
    BaseResponse<Boolean> createWebsite(WebSource webSource);
    BaseResponse<Boolean> updateWebsite(WebSource webSource);
    BaseResponse<Boolean> deteleWebsiteById(long id);
}
