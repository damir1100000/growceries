package org.example.growceries.models.user;

import lombok.*;
import org.example.growceries.entities.UserEntity;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserDTO {
    @NonNull
    private String email;
    @NonNull
    private String password;

    public UserDTO (UserEntity userEntity) {
        this.email = userEntity.getEmail();
        this.password = userEntity.getPassword();
    }
}
