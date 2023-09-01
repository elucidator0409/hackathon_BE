package com.hackathon.service;

import com.hackathon.entity.News;
import com.hackathon.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;

    @Override
    public List<News> getAllActive() {
        log.debug("getAllActive: ");
//		return newsRepository.findAllByActive(true);
        return newsRepository.findAllByActiveOrderByDateCreateDesc(true);
    }
    @Override
    public News addNews(News news) {
        news.onActive();
        return newsRepository.save(news);
    }

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public News getNewsById(Long id) {
        return newsRepository.findById(id).orElse(null);
    }


    @Override
    public News updateNews(Long id, News updatedNews) {
        News existingNews = newsRepository.findById(id).orElse(null);
        if (existingNews != null) {
            existingNews.setTitle(updatedNews.getTitle());
            existingNews.setContent(updatedNews.getContent());
            // Update other fields as needed
            return newsRepository.save(existingNews);
        }
        return null; // News with the given id not found
    }

    @Override
    public void deleteNews(Long id) {

    }

    @Override
    public Object removeNews(long id) {
        News news = getNewsById(id);
        if( news.getActive()) {
            news.deActive();
            newsRepository.delete(news);
        }
        return news;
    }

}

