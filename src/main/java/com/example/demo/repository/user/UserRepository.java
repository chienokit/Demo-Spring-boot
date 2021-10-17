package com.example.demo.repository.user;

import com.example.demo.domain.CategoryEntity;
import com.example.demo.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByUserName(String userName);
    Optional<UserEntity> findById(String code);
}
