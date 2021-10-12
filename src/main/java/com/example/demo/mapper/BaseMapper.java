package com.example.demo.mapper;

import org.mapstruct.MappingTarget;

public interface BaseMapper<Entity, DTO, Input> {
    Entity inputToEntity(Input input);
    void inputToEntity(Input input, @MappingTarget Entity entity);
    Input entityToInput(Entity entity);
    DTO entityToDTO(Entity entity);
    Entity dtoToEntity(DTO dto);
}
