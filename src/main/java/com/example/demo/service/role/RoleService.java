package com.example.demo.service.role;

import com.example.demo.service.role.dto.RoleDTO;
import org.springframework.http.ResponseEntity;

public interface RoleService {
    ResponseEntity<RoleDTO> delete(String roleCode);
}
