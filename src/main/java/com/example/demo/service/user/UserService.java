package com.example.demo.service.user;

import com.example.demo.service.news.dto.NewsDTO;
import com.example.demo.service.news.input.NewsInput;
import com.example.demo.service.user.dto.UserDTO;
import com.example.demo.service.user.input.UserInput;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<UserDTO> save(UserInput userInput);
    ResponseEntity<UserDTO> update(UserInput userInput);
    ResponseEntity<UserDTO> delete(String userName);

}
