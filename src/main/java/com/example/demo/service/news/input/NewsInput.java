package com.example.demo.service.news.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsInput {
    private String id;
    private String title;
    private String thumbnail;
    private String shortDescription;
    private String content;
    private String categoryCode;
    private boolean removed;
}
