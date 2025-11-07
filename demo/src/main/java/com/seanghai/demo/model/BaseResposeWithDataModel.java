package com.seanghai.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseResposeWithDataModel extends BaseResposeModel {
    private Object data;
    public BaseResposeWithDataModel(String message, String status , Object data) {
        super(message, status);
        this.data = data;
    }

}
