package com.seanghai.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserResponseDto {
    @JsonProperty("user_id")
    private long id;

    @JsonProperty("username")
    private String name;
    private Integer age;

    @JsonProperty ("Location")

    private String address ;
    private String email;
    private String role="USER";

}
