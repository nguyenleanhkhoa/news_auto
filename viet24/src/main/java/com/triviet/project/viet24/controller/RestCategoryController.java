package com.triviet.project.viet24.controller;


import com.google.gson.Gson;
import com.triviet.project.viet24.base.entity.BaseResponse;
import com.triviet.project.viet24.entity.Category;
import com.triviet.project.viet24.implement.ICategoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/category")
public class RestCategoryController {
    Gson gson = new Gson();

    @Autowired
    private  ICategoryService iCategoryService;


    @GetMapping(value = "/all")
    @ResponseBody
    public BaseResponse<Page<Category>> getAllCategory(@RequestParam int page) {
        return iCategoryService.findAll(page);
    }
    
    @PutMapping(value = "")
    public BaseResponse<Boolean> updateCategoryById(@RequestBody String body) {
        Category category = gson.fromJson(body,Category.class);

    	return iCategoryService.updateCategoryById( category);
    }

    @PostMapping(value = "")
    public BaseResponse<Boolean> createCategory(@RequestBody String body) {
        Category category = gson.fromJson(body,Category.class);

        return iCategoryService.createCategory( category);
    }

    @DeleteMapping(value = "")
    public BaseResponse<Boolean> deleteCategoryById(@RequestParam long id) {
        return iCategoryService.deleteCategoryById(id);
    }
}