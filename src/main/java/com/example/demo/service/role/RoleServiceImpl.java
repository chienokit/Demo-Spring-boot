package com.example.demo.service.role;

import com.example.demo.domain.RoleEntity;
import com.example.demo.repository.role.RoleRepository;
import com.example.demo.service.role.dto.RoleDTO;
import com.example.demo.service.role.mapper.RoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService{

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    @Override
    public ResponseEntity<RoleDTO> delete(String roleCode) {
        Optional<RoleEntity> optionalRoleEntity = roleRepository.findByCode(roleCode);
        if(optionalRoleEntity.isPresent()) {
            roleRepository.delete(optionalRoleEntity.get());
            return ResponseEntity.ok(roleMapper.entityToDTO(optionalRoleEntity.get()));
        } else {
            throw new RuntimeException("Role is not exist");
        }
    }
}
