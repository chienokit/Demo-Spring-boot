package com.example.demo.service.news.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsDTO {
    private String id;
    private String title;
    private String thumbnail;
    private String shortDescription;
    private String content;
    private String categoryCode;
}
