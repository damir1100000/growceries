package org.example.growceries.models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class PasswordResettingDTO extends PasswordChangingDTO{
    private String email;
    private boolean confirmedByMail = true;
}
