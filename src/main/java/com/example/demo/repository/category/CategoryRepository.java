package com.example.demo.repository.category;

import com.example.demo.domain.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
    Optional<CategoryEntity> findByCode(String code);
}
