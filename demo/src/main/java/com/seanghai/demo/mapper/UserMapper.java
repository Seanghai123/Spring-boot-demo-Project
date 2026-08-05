package com.seanghai.demo.mapper;

import com.seanghai.demo.dto.UserDto;
import com.seanghai.demo.dto.UserResponseDto;
import com.seanghai.demo.enitity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component


public class UserMapper {
    public User toEntity (UserDto dto){
        User entity = new User();
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());
        entity.setAge(dto.getAge());
        entity.setAddress(dto.getAddress());
        entity.setEmail(dto.getEmail());

        return entity;
    }
    public void  updateEntityFromDto(User entity, UserDto dto){
        if(entity == null  || dto ==null){
            return ;
        }
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setAge(dto.getAge());
        entity.setRole(dto.getRole());
        entity.setAddress(dto.getAddress());

    }
    public UserResponseDto toDto(User entity){
        UserResponseDto dto = new UserResponseDto() ;

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setAge(entity.getAge());
        dto.setAddress(entity.getAddress());
        dto.setRole(entity.getRole());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }
    public List< UserResponseDto> toDtoList(List<User> entitist){
      if (entitist == null  || entitist.isEmpty()){
          return new ArrayList<>();
      }
      return entitist.stream().map(user -> this.toDto(user)).collect(Collectors.toList());
    }
}
