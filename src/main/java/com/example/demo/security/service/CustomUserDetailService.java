package com.example.demo.security.service;

import com.example.demo.domain.UserEntity;
import com.example.demo.repository.user.UserRepository;
import com.example.demo.security.userdetail.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUserName(userName);
        if (!optionalUserEntity.isPresent()) {
            throw new UsernameNotFoundException("Login fail! User name is not exist");
        } else {
            UserEntity userEntity = optionalUserEntity.get();
            CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);
            return customUserDetails;
        }
    }
    // JWTAuthenticationFilter sẽ sử dụng hàm này
    @Transactional
    public UserDetails loadUserById(String id) {
        UserEntity user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return new CustomUserDetails(user);
    }

}
