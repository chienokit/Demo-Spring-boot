package com.example.demo.service.category;

import com.example.demo.domain.CategoryEntity;
import com.example.demo.repository.category.CategoryRepository;
import com.example.demo.service.category.dto.CategoryDTO;
import com.example.demo.service.category.mapper.CategoryMapper;
import com.example.demo.service.news.dto.NewsDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @Override
    public ResponseEntity<CategoryDTO> delete(String categoryCode) {
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findByCode(categoryCode);
        if(optionalCategoryEntity.isPresent()) {
            categoryRepository.delete(optionalCategoryEntity.get());
            return ResponseEntity.ok(categoryMapper.entityToDTO(optionalCategoryEntity.get()));
        } else {
            throw new RuntimeException("Category is not exits");
        }
    }
}
