package org.example.growceries.services;

import org.example.growceries.entities.UserEntity;
import org.example.growceries.models.user.PasswordResettingDTO;
import org.example.growceries.models.user.RegisteringUserDTO;
import org.example.growceries.models.user.UserDTO;
import org.example.growceries.repositories.UserRepository;
import org.example.growceries.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    CustomUserDetailsService securityUserDetailsService;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepository userRepository;

    public UserDTO createUser (RegisteringUserDTO userDTO) {
        if(this.userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Already registered");
        }

        if(!userDTO.getPassword().equals(userDTO.getRetypedPassword())) {
            throw new RuntimeException("Passwords don't match");
        }
        UserEntity userEntity = new UserEntity(userDTO);
        userEntity.setPassword(this.encoder.encode(userDTO.getPassword()));
        this.userRepository.save(userEntity);
        return new UserDTO(userDTO.getEmail(), userDTO.getPassword());
    };

    public List<UserDTO> getAllUsers () {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<UserEntity> userEntities = this.userRepository.findAll();
        for(UserEntity userEntity : userEntities) {
            userDTOS.add(new UserDTO(userEntity));
        }
        return userDTOS;
    }

    public UserDTO getUserById (Long userId) {
        return new UserDTO(this.returnIfAvailable(userId));
    }

    public Boolean resetPassword (PasswordResettingDTO passwordResettingDTO) {
        UserEntity userEntity = this.returnIfAvailable(passwordResettingDTO.getEmail());
        if(!passwordResettingDTO.isConfirmedByMail()) {
            throw new RuntimeException("Unauthorizated");
        }
        userEntity.setPassword(passwordResettingDTO.getNewPassword());
        this.userRepository.save(userEntity);
        return true;
    }

    public Boolean deleteUser (Long userId) {
        UserEntity entity = this.returnIfAvailable(userId);
        this.userRepository.delete(entity);
        return true;
    }

    private UserEntity returnIfAvailable (Long userId) {
        Optional<UserEntity> userEntityOpt = this.userRepository.findById(userId);
        if(userEntityOpt.isEmpty()) {
            throw new RuntimeException("User doesn't exist");
        }
        return userEntityOpt.get();
    }
    private UserEntity returnIfAvailable (String email) {
        Optional<UserEntity> userEntityOpt = this.userRepository.findByEmail(email);
        if(userEntityOpt.isEmpty()) {
            throw new RuntimeException("User doesn't exist");
        }
        return userEntityOpt.get();
    }
}
