package com.example.demo.web.rest.news;

import com.example.demo.service.news.NewsService;
import com.example.demo.service.news.dto.NewsDTO;
import com.example.demo.service.news.input.NewsInput;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/news")
public class NewsAPI {
    private final NewsService newsService;

    @GetMapping
    public ResponseEntity<List<NewsDTO>> findAll(@RequestParam(value = "pageIndex", required = false) Integer pageIndex,
                                                 @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        List<NewsDTO> newsDTOList;
        if(pageIndex != null && pageSize != null) {
            return newsService.findAll(pageSize, pageIndex);
        } else {
            return newsService.findAll();
        }
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<NewsDTO> save(@RequestBody NewsInput newsInput) {
        return newsService.save(newsInput);
    }

    @PutMapping("/{newsId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<NewsDTO> update(@RequestBody NewsInput newsInput, @PathVariable String newsId) {
        return newsService.update(newsInput, newsId);
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<String[]> delete(@RequestBody String[] ids) {
        return newsService.delete(ids);
    }
}
