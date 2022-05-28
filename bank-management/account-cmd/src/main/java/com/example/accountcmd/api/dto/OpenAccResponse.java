package com.example.accountcmd.api.dto;

import com.example.accountdomain.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class OpenAccResponse extends BaseResponse {

    private String id;

    public OpenAccResponse(String message, String id){
        super(message);
        this.id = id;
    }
}
