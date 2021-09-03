package com.triviet.project.viet24.controller;

import com.triviet.project.viet24.implement.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.triviet.project.viet24.base.entity.BaseResponse;
import com.triviet.project.viet24.entity.News;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/news")
public class RestNewsController {

	@Autowired
	private INewsService iNewsService;

	@GetMapping(value = "/all")
	@ResponseBody
	public BaseResponse<News> getAllNews(@RequestParam(name = "page") int page) {
		return iNewsService.getAllNews(page);
	}
}
