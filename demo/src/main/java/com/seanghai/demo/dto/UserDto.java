package com.seanghai.demo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;// ← ADD THIS\
    private String password;
    private Integer age;
    private String address;
    private String email;
    private String role = "USER";

//    private String role = "USER"; ហៅថា predefined value
    // constructors, getters, setters
}
    //Default Constructor (No Parameter)
    //Constructor with parameter មានឈ្មោះដូចគ្នាគេហៅថា Overloading Method
    //Getters and Setters
    //Getters បង្កើត for class ដទៃទៀតទាញយកតម្លៃខាងលើទៅប្រើបាន

    //setters ប្រើសម្រាប់អោយគេset តម្លៃមកអោយយើងវិញ


