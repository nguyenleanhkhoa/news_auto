package com.triviet.project.viet24.implement;

import java.util.List;

import com.triviet.project.viet24.base.entity.BaseResponse;
import com.triviet.project.viet24.entity.Category;
import org.springframework.data.domain.Page;

public interface ICategoryService {
	 	BaseResponse<Page<Category>>findAll(int page);
	 	BaseResponse<List<Category>> findAllCategoryByStatus(int status);
	 	BaseResponse<List<Category>> findCategoryIdByNews(String category);
	 	BaseResponse<List<Category>> findCategoryByDisplay(int display);
	 	BaseResponse<Object> showQuantityNewsOfCategory(int display);
	 	BaseResponse<Boolean> createCategory(Category category);
	 	BaseResponse<Boolean> updateCategoryById( Category category);
	 	BaseResponse<Boolean> deleteCategoryById(long id);
}
