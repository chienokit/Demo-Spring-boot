package com.example.demo.service.user.mapper;

import com.example.demo.domain.UserEntity;
import com.example.demo.mapper.BaseMapper;
import com.example.demo.service.user.dto.UserDTO;
import com.example.demo.service.user.input.UserInput;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<UserEntity, UserDTO, UserInput> {
}
