package com.example.HealthCare.mapper;

import com.example.HealthCare.dto.UserLoginDTO;
import com.example.HealthCare.dto.UserRegisterDTO;
import com.example.HealthCare.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUserL(UserLoginDTO userLoginDTO);
    User toUserR(UserRegisterDTO userRegisterDTO);
    UserLoginDTO toUserLoginDTO(User user);
    UserRegisterDTO toUserRegisterDTO(User user);
}
