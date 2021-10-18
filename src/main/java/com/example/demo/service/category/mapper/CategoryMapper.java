package com.example.demo.service.category.mapper;

import com.example.demo.domain.CategoryEntity;
import com.example.demo.mapper.BaseMapper;
import com.example.demo.service.category.dto.CategoryDTO;
import com.example.demo.service.category.input.CategoryInput;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends BaseMapper<CategoryEntity, CategoryDTO, CategoryInput> {
}
