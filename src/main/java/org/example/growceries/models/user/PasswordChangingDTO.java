package org.example.growceries.models.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PasswordChangingDTO {
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;
}
