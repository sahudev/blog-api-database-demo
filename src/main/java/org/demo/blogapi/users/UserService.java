package org.demo.blogapi.users;

import org.demo.blogapi.users.dto.CreateUserDTO;
import org.demo.blogapi.users.dto.LoginUserDTO;
import org.demo.blogapi.users.dto.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    public UserResponseDTO createUser(CreateUserDTO createUserDTO){
        // TODO Encrypt Password
        // TODO Validate email
        // TODO Check if username already exists
        var newUserEntity = modelMapper.map(createUserDTO,UserEntity.class);
        newUserEntity.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        var savedUser = userRepository.save(newUserEntity);
        var userResponseDTO = modelMapper.map(savedUser, UserResponseDTO.class);
        return userResponseDTO;
    }

    public UserResponseDTO loginUser(LoginUserDTO loginUserDTO){

        var userEntity = userRepository.findByUsername(loginUserDTO.getUsername());
        if(userEntity==null){
          throw new UserNotFoundException(loginUserDTO.getUsername());
        }
        var passMatch = passwordEncoder.matches(loginUserDTO.getPassword(),userEntity.getPassword());
        if (!passMatch){
            throw new IncorrectPasswordException();
        }
        var userResponseDTO = modelMapper.map(userEntity, UserResponseDTO.class);
        return userResponseDTO;
    }

    public static class UserNotFoundException extends IllegalArgumentException{
        public UserNotFoundException(Integer id){
            super("User Id with id: " + id + " not found");
        }

        public UserNotFoundException(String username){
            super("User with username: " + username + " not found");
        }
    }

    public static class IncorrectPasswordException extends IllegalArgumentException{
        public IncorrectPasswordException(){
            super("Incorrect Password");
        }
    }
}
