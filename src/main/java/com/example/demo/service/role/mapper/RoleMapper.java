package com.example.demo.service.role.mapper;

import com.example.demo.domain.RoleEntity;
import com.example.demo.mapper.BaseMapper;
import com.example.demo.service.role.dto.RoleDTO;
import com.example.demo.service.role.input.RoleInput;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends BaseMapper<RoleEntity, RoleDTO, RoleInput> {
}
