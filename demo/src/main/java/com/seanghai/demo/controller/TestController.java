package com.seanghai.demo.controller;

import com.seanghai.demo.model.BaseResposeModel;
import com.seanghai.demo.model.BaseResposeWithDataModel;
import com.seanghai.demo.model.UserModel;
import com.seanghai.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")

public class TestController {

    @Autowired
    private UserService userService;
    @GetMapping()
//    List an Usermodel Respone to User
    private ResponseEntity<BaseResposeWithDataModel> ListUsers() {
//        System.out.println(users.get(0));
        return userService.ListUsers();
    }
    @GetMapping("/{user_id}")
    public ResponseEntity<BaseResposeWithDataModel>getUser(@PathVariable("user_id") Long userId){
        return userService.getUser(userId);
    }

    // used for creating/inserting record
    // request body can be called request payload or shortcut "payload"
    @PostMapping()
    public ResponseEntity<BaseResposeModel> createUser(@RequestBody UserModel payload) {
     return userService.createUser(payload) ;
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<BaseResposeModel> updateUser(@PathVariable("user_id") Long userId, @RequestBody UserModel payload) {
        return userService.updateUser(payload,userId);
    }
    @DeleteMapping("/{user_id}")
    public ResponseEntity<BaseResposeModel> deleteUser(@PathVariable("user_id") Long userId) {
        //not found, every start from 0jjjjj
       return userService.deleteUser(userId);
    }
}
