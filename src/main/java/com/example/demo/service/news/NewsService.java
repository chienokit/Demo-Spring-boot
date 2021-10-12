package com.example.demo.service.news;

import com.example.demo.service.news.dto.NewsDTO;
import com.example.demo.service.news.input.NewsInput;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NewsService {
    ResponseEntity<NewsDTO> save(NewsInput newsInput);
    ResponseEntity<NewsDTO> update(NewsInput newsInput, String newId);
    ResponseEntity<String[]> delete(String[] ids);
    ResponseEntity<List<NewsDTO>>findAll();
    ResponseEntity<List<NewsDTO>>findAll(Integer pageSize, Integer pageIndex);


}
