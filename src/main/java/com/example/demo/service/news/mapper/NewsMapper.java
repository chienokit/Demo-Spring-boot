package com.example.demo.service.news.mapper;

import com.example.demo.service.news.dto.NewsDTO;
import com.example.demo.service.news.input.NewsInput;
import com.example.demo.domain.NewsEntity;
import com.example.demo.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsMapper extends BaseMapper<NewsEntity, NewsDTO, NewsInput> {
}
