package com.hackathon.controller;

import com.hackathon.entity.News;
import com.hackathon.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/news")
public class NewsApiController {
    @Autowired
    private NewsService newsService;

    @GetMapping
    public List<News> getAllNews() {
        log.debug("getAllNews: ");
        return newsService.getAllNews();
    }

    @GetMapping("/{id}")
    public News getNewsById(@PathVariable Long id) {
        log.debug("getNewsByID: ");
        return newsService.getNewsById(id);
    }



    @PutMapping("/{id}")
    public News updateNews(@PathVariable Long id, @RequestBody News updatedNews) {
        log.debug("updateNews: ");
        return newsService.updateNews(id, updatedNews);
    }

    @DeleteMapping("/{id}")
    public void deleteNews(@PathVariable Long id) {
        log.debug("delete news: ");
        newsService.deleteNews(id);
    }
}
