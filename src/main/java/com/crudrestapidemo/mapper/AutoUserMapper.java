package com.crudrestapidemo.mapper;

import com.crudrestapidemo.dto.UserDto;
import com.crudrestapidemo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {
    // Instance
    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    // If we have different Field Names
    // @Mapping(source="email", target="email")
    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);
}
