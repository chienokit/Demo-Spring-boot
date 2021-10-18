package com.example.demo.service.user;

import com.example.demo.domain.RoleEntity;
import com.example.demo.domain.UserEntity;
import com.example.demo.repository.role.RoleRepository;
import com.example.demo.repository.user.UserRepository;
import com.example.demo.service.user.dto.UserDTO;
import com.example.demo.service.user.input.UserInput;
import com.example.demo.service.user.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<UserDTO> save(UserInput userInput) {
        UserEntity userEntity;
        String[] roleCodes = userInput.getRoleCodes();
        userEntity = userMapper.inputToEntity(userInput);
        // Mã hóa mật khẩu người dùng
        userEntity.setPassWord(passwordEncoder.encode(userEntity.getPassWord()));
        List<RoleEntity> roleEntities = new ArrayList<>();
        for (String roleCode : roleCodes) {
            Optional<RoleEntity> optionalRoleEntity = roleRepository.findByCode(roleCode);
            if(optionalRoleEntity.isPresent()) {
                roleEntities.add(optionalRoleEntity.get());
            } else {
                throw new RuntimeException("role is not exist !");
            }
        }
        userEntity.setRoles(roleEntities);
        userRepository.save(userEntity);
        return ResponseEntity.ok(userMapper.entityToDTO(userEntity));
    }

    @Override
    public ResponseEntity<UserDTO> update(UserInput userInput, String userID) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(userID);
        if(optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            // Kiểm tra mật khẩu có thay đổi không nếu thay đổi thì phải mã hóa
            if(!userInput.getPassWord().equals(userEntity.getPassWord())) {
                userInput.setPassWord(passwordEncoder.encode(userInput.getPassWord()));
            }
            userMapper.inputToEntity(userInput, userEntity);
            String[] roleCodes = userInput.getRoleCodes();
            List<RoleEntity> roleEntities = new ArrayList<>();
            for(String roleCode : roleCodes) {
                Optional<RoleEntity> optionalRoleEntity =  roleRepository.findByCode(roleCode);
                if(optionalRoleEntity.isPresent()) {
                    roleEntities.add(optionalRoleEntity.get());
                } else {
                    throw new RuntimeException("role is not exist");
                }
            }
            userEntity.setRoles(roleEntities);
            userRepository.save(userEntity);
            return ResponseEntity.ok(userMapper.entityToDTO(userEntity));
        }
        else {
            throw new RuntimeException("User is not exist");
        }
    }

    @Override
    public ResponseEntity<UserDTO> delete(String userName) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUserName(userName);
        if(optionalUserEntity.isPresent()) {
            userRepository.delete(optionalUserEntity.get());
        }
        return ResponseEntity.ok(userMapper.entityToDTO(optionalUserEntity.get()));
    }
}
