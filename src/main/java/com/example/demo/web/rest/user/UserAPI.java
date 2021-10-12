package com.example.demo.web.rest.user;

import com.example.demo.service.user.UserService;
import com.example.demo.service.user.dto.UserDTO;
import com.example.demo.service.user.input.UserInput;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/user")
public class UserAPI {

    private final UserService userService;
    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody UserInput userInput) {
        return userService.save(userInput);
    }


    //@PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/hello")
    public ResponseEntity dfd(@RequestBody UserInput userInput) {
        return ResponseEntity.ok("ok");
    }
}
