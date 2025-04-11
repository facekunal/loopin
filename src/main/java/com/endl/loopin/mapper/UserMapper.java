package com.endl.loopin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.endl.loopin.dto.SignUpUserDto;
import com.endl.loopin.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // Mapping from Entity -> DTO (for client display, might not require) TODO: @kunal remove
    SignUpUserDto toDto(User user);

    // Mapping from DTO -> Entity (for saving to DB)
    @Mapping(target = "isEmailVerified", ignore = true) // set manually
    @Mapping(target = "isTwoFactorEnabled", ignore = true) // set manually
    User toEntity(SignUpUserDto dto);
}

