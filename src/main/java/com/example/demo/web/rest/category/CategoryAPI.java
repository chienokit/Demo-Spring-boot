package com.example.demo.web.rest.category;

import com.example.demo.repository.category.CategoryRepository;
import com.example.demo.service.category.CategoryService;
import com.example.demo.service.category.dto.CategoryDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/category")
public class CategoryAPI {
    private final CategoryService categoryService;

//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{categoryCode}")
    public ResponseEntity<CategoryDTO> delete(@PathVariable String categoryCode) {
        return categoryService.delete(categoryCode);
    }

}
