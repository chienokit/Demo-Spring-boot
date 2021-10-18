package com.example.demo.service.category;

import com.example.demo.service.category.dto.CategoryDTO;
import com.example.demo.service.news.dto.NewsDTO;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResponseEntity<CategoryDTO> delete(String categoryCode);
}
