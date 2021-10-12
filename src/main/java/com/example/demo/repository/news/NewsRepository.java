package com.example.demo.repository.news;

import com.example.demo.domain.NewsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, String> {
    Optional<NewsEntity> findOneById(String id);

}
