package com.seanghai.demo.service;

import com.seanghai.demo.enitity.User;
import com.seanghai.demo.model.BaseResposeModel;
import com.seanghai.demo.model.BaseResposeWithDataModel;
import com.seanghai.demo.model.UserModel;
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

    private List<UserModel> users = new ArrayList<>(Arrays.asList(new UserModel(1l, "Seang hai", 23, "pp","","")));


    public ResponseEntity<BaseResposeWithDataModel> ListUsers(){
//        បង្កើត List ហៅ User ពី package enitity ដើម្បី get data ពី database ដោយប្រើ findAll method ដែរមានស្រាប់
        List<User> userData = userRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResposeWithDataModel("success", "successfully retrieve user", userData));
    }
    public ResponseEntity<BaseResposeWithDataModel> getUser(Long userID){
        Optional<User> user = userRepository.findById(userID);
        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResposeWithDataModel("Fail","user not found with ID"+userID,null));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResposeWithDataModel("success","successfully retrieve user",user.get()));
    }
    public ResponseEntity<BaseResposeModel> createUser(UserModel payload) {
        User user = new User(); // Correct class and variable usage

        user.setName(payload.getName());
        user.setAddress(payload.getAddress());
        user.setAge(payload.getAge());
        user.setEmail(payload.getEmail());
        user.setCreatedAt(LocalDateTime.now());
        user.setRole(payload.getRole());

        userRepository.save(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new BaseResposeModel("Successfully created user", "success"));
    }

    public ResponseEntity<BaseResposeModel> updateUser(UserModel payload, Long userId){
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
        User updatedUser = exisiting.get();
        updatedUser.setEmail(payload.getEmail());
        updatedUser.setName(payload.getName());
        updatedUser.setAge(payload.getAge());
        updatedUser.setAddress(payload.getAddress());
        updatedUser.setRole(payload.getRole());
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

