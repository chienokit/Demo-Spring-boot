package com.example.demo.security.userdetail;

import com.example.demo.domain.RoleEntity;
import com.example.demo.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    UserEntity user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<RoleEntity> roleEntityList = user.getRoles();
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for(RoleEntity roleEntity : roleEntityList) {
            GrantedAuthority grantedAuthority;
            if(roleEntity.getCode().equals("user")) {
                grantedAuthority = new SimpleGrantedAuthority("USER");
                authorityList.add(grantedAuthority);
            }
            if(roleEntity.getCode().equals("admin")) {
                grantedAuthority = new SimpleGrantedAuthority("ADMIN");
                authorityList.add(grantedAuthority);
            }
        }
        return authorityList;
    }

    @Override
    public String getPassword() {
        return user.getPassWord();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
