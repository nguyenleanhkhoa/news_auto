package com.triviet.project.viet24.controller;

import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.triviet.project.viet24.base.entity.BaseResponse;
import com.triviet.project.viet24.entity.Category;
import com.triviet.project.viet24.entity.WebSource;
import com.triviet.project.viet24.implement.IWebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/website")
public class RestWebSourceController {
    Gson gson = new Gson();
    @Autowired
    private IWebsiteService iWebsiteService;

    @GetMapping(value = "/all")
    @ResponseBody
    public BaseResponse<Page<WebSource>> getAllWebsite(@RequestParam int page) {
        return iWebsiteService.findAll(page);
    }

    @PostMapping(value = "")
    @ResponseBody
    public BaseResponse<Boolean> createWebsite(@RequestBody String body) {
        WebSource webSource = gson.fromJson(body,WebSource.class);
        return iWebsiteService.createWebsite(webSource);
    }

    @PutMapping(value = "")
    public BaseResponse<Boolean> updateWebsiteById(@RequestBody String body) {
        WebSource webSource = gson.fromJson(body,WebSource.class);
        return iWebsiteService.updateWebsite(webSource);
    }

    @DeleteMapping(value = "")
    @ResponseBody
    public BaseResponse<Boolean> deleteWebsite(@RequestParam long id) {
        return iWebsiteService.deteleWebsiteById(id);
    }
}
