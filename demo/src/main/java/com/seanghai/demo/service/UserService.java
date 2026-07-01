package com.seanghai.demo.service;

import com.seanghai.demo.dto.UserResponseDto;
import com.seanghai.demo.enitity.User;
import com.seanghai.demo.mapper.UserMapper;
import com.seanghai.demo.model.BaseResposeModel;
import com.seanghai.demo.model.BaseResposeWithDataModel;
import com.seanghai.demo.dto.UserDto;
import com.seanghai.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper mapper;



    public ResponseEntity<BaseResposeWithDataModel> ListUsers(){
//        បង្កើត List ហៅ User ពី package enitity ដើម្បី get data ពី database ដោយប្រើ findAll method ដែរមានស្រាប់
        List<User> users = userRepository.findAll();

        List<UserResponseDto> dtos = mapper.toDtoList(users);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResposeWithDataModel("success", "successfully retrieve user", dtos));
    }
    public ResponseEntity<BaseResposeWithDataModel> getUser(Long userID){



        Optional<User> user = userRepository.findById(userID);
        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResposeWithDataModel("Fail","user not found with ID"+userID,null));
        }
        UserResponseDto dto = mapper.toDto(user.get( ));
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResposeWithDataModel("success","successfully retrieve user",user.get()));
    }
    public ResponseEntity<BaseResposeModel> createUser(UserDto payload) {
        User user = mapper.toEntity(payload);
        userRepository.save(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new BaseResposeModel("Successfully created user", "success"));
    }

    public ResponseEntity<BaseResposeModel> updateUser(UserDto payload, Long userId){
        Optional<User> exisiting = userRepository.findById(userId);
//        isEmpty រកមិនឃើញ
//        if user not found , then response 404
        if (exisiting.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new BaseResposeModel("Fail", "User Not Found with id:"+userId));
        }
//        users.forEach(user -> {
//            if (user.getId() == (userId)) {
//                user.setName(payload.getName());
//                user.setAge(payload.getAge());
//                user.setAddress(payload.getAddress());
//            }
//        }); //យើងធ្វើការ loop user
//        user found
//        id input 100 , it will create new user-> 101
//        update user
        User updatedUser  = exisiting.get();
        mapper.updateEntityFromDto(updatedUser,payload);

        userRepository.save(updatedUser);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseResposeModel("Successfully updated user", "success"));
    }

    public ResponseEntity<BaseResposeModel>deleteUser( Long userId){
       if(!userRepository.existsById(userId)){
           return ResponseEntity
                   .status(HttpStatus.NOT_FOUND)
                   .body(new BaseResposeModel("Fail", "User Not Found with id:"+userId));
       }

       userRepository.deleteById(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseResposeModel("Successfully Delete user", "success"+userId));

    }
    }

