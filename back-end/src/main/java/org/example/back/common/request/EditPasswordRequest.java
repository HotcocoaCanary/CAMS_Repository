package org.example.back.common.request;

import lombok.Data;

@Data
public class EditPasswordRequest {
    private String id;
    private String password;
    private String newPassword;
}
