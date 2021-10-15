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

    @Override
    public ResponseEntity<UserDTO> save(UserInput userInput) {
        UserEntity userEntity;
        String[] roleCodes = userInput.getRoleCodes();
        userEntity = userMapper.inputToEntity(userInput);
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
        UserEntity userEntity;
        String[] roleCodes = userInput.getRoleCodes();
        userMapper.inputToEntity(userInput, userEntity);
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

        return null;
    }

    @Override
    public ResponseEntity<UserDTO> delete(String userName) {
        return null;
    }
}
