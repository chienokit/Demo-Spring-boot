package com.example.demo.web.rest.role;

import com.example.demo.service.role.RoleService;
import com.example.demo.service.role.dto.RoleDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/role")
public class RoleAPI {
    private final RoleService roleService;

    //@PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{roleCode}")
    public ResponseEntity<RoleDTO> delete(@PathVariable String roleCode) {
        return roleService.delete(roleCode);
    }
}
