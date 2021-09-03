package com.triviet.project.viet24.service;

import com.google.gson.Gson;
import com.triviet.project.viet24.base.entity.BaseResponse;
import com.triviet.project.viet24.entity.Category;
import com.triviet.project.viet24.entity.WebSource;
import com.triviet.project.viet24.implement.IWebsiteService;
import com.triviet.project.viet24.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class WebsiteService implements IWebsiteService {

    @Autowired
    private WebsiteRepository websiteRepository;

    @Override
    public BaseResponse<Page<WebSource>> findAll(int page) {
        Page<WebSource> listCategory = websiteRepository.finalAllWebsite(PageRequest.of(page, 7));
        return new BaseResponse<Page<WebSource>>(200, "success", listCategory);
    }

    @Override
    public BaseResponse<Boolean> createWebsite(WebSource webSource) {
       try {
           WebSource webSourceExist = websiteRepository.findWebSourceByUrl(webSource.getURL());
           if(webSourceExist != null) {
               return new BaseResponse<Boolean>(403, "Website đã tồn tại", false );
           } else {
               websiteRepository.save(webSource);
               return new BaseResponse<Boolean>(200, "Đã thêm website thành công", true );
           }
       }catch (Exception e) {
           return new BaseResponse<Boolean>(400, "Thêm website thất bại", true );
       }

    }

    @Override
    public BaseResponse<Boolean> updateWebsite(WebSource webSource) {
        try {

            WebSource webSourceTmp = websiteRepository.findwebSourceById(webSource.getID());
            if(webSourceTmp != null) {
                webSourceTmp.setName(webSource.getName());
                webSourceTmp.setStatus(webSource.getStatus());
                webSourceTmp.setURL(webSource.getURL());

                websiteRepository.save(webSourceTmp);
                return new BaseResponse<Boolean>(200, "Thêm website thất bại!", true );
            } else {
                return new BaseResponse<Boolean>(500, "Website không tồn tại!", false );

            }
        } catch(Exception e) {
            return new BaseResponse<Boolean>(400, "Cập nhật website thất bại!", false );

        }
    }

    @Override
    public BaseResponse<Boolean> deteleWebsiteById(long id) {
        try {
            WebSource webSource = websiteRepository.findwebSourceById(id);
            if(webSource != null) {
                websiteRepository.delete(webSource);
                return new BaseResponse<Boolean>(200, "Xoá website thành công!", true);
            } else {
                return new BaseResponse<Boolean>(200, "Website không tồn tại!", false);
            }
        } catch(Exception e) {
            return new BaseResponse<Boolean>(400, "Xoá website thất bại!", false );
        }
    }

}
