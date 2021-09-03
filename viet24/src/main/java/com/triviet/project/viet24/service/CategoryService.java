package com.triviet.project.viet24.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.triviet.project.viet24.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.triviet.project.viet24.base.entity.BaseResponse;
import com.triviet.project.viet24.entity.Category;
import com.triviet.project.viet24.implement.ICategoryService;

import java.util.Date;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	 
	@Override
	public BaseResponse<Page<Category>> findAll(int page) {
		Page<Category> listCategory =  categoryRepository.findAllCategory( PageRequest.of(page, 7));
		BaseResponse<Page<Category>> asd =  new BaseResponse<Page<Category>>(200,"Success", listCategory);
		return new BaseResponse<Page<Category>>(200,"Success", listCategory);
	}

	@Override
	public BaseResponse<List<Category>> findAllCategoryByStatus(int status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse<List<Category>> findCategoryIdByNews(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse<List<Category>> findCategoryByDisplay(int display) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse<Object> showQuantityNewsOfCategory(int display) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse<Boolean> createCategory(Category category) {
		try {
			Category cate = categoryRepository.findCategoryByName(category.getName());
			if(cate != null) {
				return new BaseResponse<Boolean>(403,"Chuyên mục đã tồn tại!",true);
			} else
			{
				category.setCreatedAt((int) (new Date().getTime()/1000));
				category.setUpdatedAt((int) (new Date().getTime()/1000));
				categoryRepository.save(category);
				return new BaseResponse<Boolean>(200,"Thêm mới thành công!",true);
			}
		} catch(Exception e) {
			return new BaseResponse<Boolean>(400,"Thêm mới thất bại! ",false);
		}
	}

	@Override
	public BaseResponse<Boolean> updateCategoryById(Category category) {
		Category cate = categoryRepository.findCategoryById(category.getID());
		if(cate != null) {
			category.setUpdatedAt((int) (new Date().getTime()/1000));
			categoryRepository.save(category);
			return new BaseResponse<Boolean>(200,"Cập nhật thàng công!",true);
		}
		return new BaseResponse<Boolean>(500,"Cập nhật thất bại!",false);
	}

	@Override
	public BaseResponse<Boolean> deleteCategoryById(long id) {
		Category cate = categoryRepository.findCategoryById(id);
		if(cate != null) {
			categoryRepository.delete(cate);
			return new BaseResponse<Boolean>(200,"Xoá chuyên mục thàng công!",true);

		}
		return new BaseResponse<Boolean>(500,"Xoá chuyên mục thất bại!",false);

	}


}
