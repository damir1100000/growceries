package org.example.growceries.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.growceries.models.user.UserDTO;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
public class UserEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String email;
    @Column
    private String password;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartEntity> cartEntities;

    public UserEntity () {

    }

    public UserEntity (UserDTO userDTO) {
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
