package org.demo.blogapi.users;

import org.demo.blogapi.users.dto.CreateUserDTO;
import org.demo.blogapi.users.dto.LoginUserDTO;
import org.demo.blogapi.users.dto.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    public UserResponseDTO createUser(CreateUserDTO createUserDTO){
        var newUserEntity = modelMapper.map(createUserDTO,UserEntity.class);
        var savedUser = userRepository.save(newUserEntity);
        var userResponseDTO = modelMapper.map(savedUser, UserResponseDTO.class);
        return userResponseDTO;
    }

    public UserResponseDTO loginUser(LoginUserDTO loginUserDTO){
        // TODO Encrypt Password
        // TODO Validate email
        // TODO Check if username already exists

        var userEntity = userRepository.findByUsername(loginUserDTO.getUsername());
        if(userEntity==null){
          throw new UserNotFoundException(loginUserDTO.getUsername());
        }

        // TODO Encrypt Password
        if (loginUserDTO.getPassword()!=userEntity.getPassword()){
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
