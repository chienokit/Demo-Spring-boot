package com.example.demo.config;

import com.example.demo.domain.RoleEntity;
import com.example.demo.domain.UserEntity;
import com.example.demo.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
            List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
            List<RoleEntity> roleEntityList = userEntity.getRoles();
            for (RoleEntity roleEntity : roleEntityList) {
                GrantedAuthority grantedAuthority = null;
                if (Objects.equals(roleEntity.getCode(), "admin")) {
                    grantedAuthority = new SimpleGrantedAuthority("ADMIN");
                }
                if (roleEntity.getCode().equals("user")) {
                    grantedAuthority = new SimpleGrantedAuthority("USER");
                }
                grantedAuthorityList.add(grantedAuthority);
            }
            CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);
            //UserDetails userDetails = new User(userEntity.getUserName(), userEntity.getPassWord(), grantedAuthorityList);
            //return userDetails;
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
