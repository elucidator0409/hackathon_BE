package com.hackathon.service;

import com.hackathon.entity.News;

import java.util.List;

public interface NewsService {
    List<News> getAllNews();
    News getNewsById(Long id);
    News updateNews(Long id, News news);
    void deleteNews(Long id);

    News addNews(News news);

    List<News> getAllActive();

    Object removeNews(long id);



}
