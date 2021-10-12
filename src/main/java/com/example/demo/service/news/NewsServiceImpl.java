package com.example.demo.service.news;

import com.example.demo.domain.NewsEntity_;
import com.example.demo.repository.category.CategoryRepository;
import com.example.demo.repository.news.NewsRepository;
import com.example.demo.service.news.dto.NewsDTO;
import com.example.demo.service.news.input.NewsInput;
import com.example.demo.service.news.mapper.NewsMapper;
import com.example.demo.domain.CategoryEntity;
import com.example.demo.domain.NewsEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NewsServiceImpl implements NewsService{

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<NewsDTO> save(NewsInput newsInput) {
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findByCode(newsInput.getCategoryCode());
        NewsEntity newsEntity = newsMapper.inputToEntity(newsInput);
        if(optionalCategoryEntity.isPresent()) {
            CategoryEntity categoryEntity = optionalCategoryEntity.get();
            newsEntity.setCategory(categoryEntity);
        }
        newsRepository.save(newsEntity);
        NewsDTO newsDTO = newsMapper.entityToDTO(newsEntity);
        newsDTO.setCategoryCode(newsInput.getCategoryCode());
        return ResponseEntity.ok().body(newsDTO);
    }

    @Override
    public ResponseEntity<NewsDTO> update(NewsInput newsInput, String newId) {
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findByCode(newsInput.getCategoryCode());
        Optional<NewsEntity> optionalNewsEntityById = newsRepository.findOneById(newId);
        NewsEntity newsEntity;
        if(optionalNewsEntityById.isPresent() && optionalCategoryEntity.isPresent()) {
            newsEntity = optionalNewsEntityById.get();
            //update newsEntity
            newsMapper.inputToEntity(newsInput, newsEntity);
            newsEntity.setId(newId);
            CategoryEntity categoryEntity = optionalCategoryEntity.get();
            newsEntity.setCategory(categoryEntity);
            newsRepository.save(newsEntity);
            NewsDTO newsDTO = newsMapper.entityToDTO(newsEntity);
            newsDTO.setCategoryCode(newsInput.getCategoryCode());
            return ResponseEntity.ok(newsDTO);
        } else {
            throw new RuntimeException("News is not existed or Category is not existed");
        }
    }

    @Override
    public ResponseEntity<String[]> delete(String[] ids) {
        Optional<NewsEntity> newsEntityOptionalById;
        for(String id : ids) {
            newsEntityOptionalById = newsRepository.findOneById(id);
            if(!newsEntityOptionalById.isPresent()) {
                throw  new RuntimeException("News is not exsited !");
            } else {
                newsRepository.deleteById(id);
            }
        }
        return ResponseEntity.ok(ids);
    }

    @Override
    public ResponseEntity<List<NewsDTO>> findAll() {

        List<NewsDTO> result = new ArrayList<>();
        List<NewsEntity> entities = newsRepository.findAll();
        for(NewsEntity entity : entities) {
            NewsDTO newsDTO;
            newsDTO = newsMapper.entityToDTO(entity);
            result.add(newsDTO);
        }
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<NewsDTO>> findAll(Integer pageSize, Integer pageIndex) {
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        List<NewsDTO> result = new ArrayList<>();
        List<NewsEntity> entities = newsRepository.findAll(pageable).getContent();
        for(NewsEntity entity : entities) {
            NewsDTO newsDTO;
            newsDTO = newsMapper.entityToDTO(entity);
            result.add(newsDTO);
        }
        return ResponseEntity.ok(result);
    }
}
