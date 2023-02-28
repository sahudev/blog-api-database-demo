package org.demo.blogapi.users;

import org.demo.blogapi.security.AuthTokens.AuthTokenService;
import org.demo.blogapi.users.dto.CreateUserDTO;
import org.demo.blogapi.users.dto.LoginUserDTO;
import org.demo.blogapi.users.dto.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.demo.blogapi.security.jwt.JWTService;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthTokenService authTokenService;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, JWTService jwtService, AuthTokenService authTokenService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authTokenService = authTokenService;
    }

    public UserResponseDTO createUser(CreateUserDTO createUserDTO){
        // TODO Validate email
        // TODO Check if username already exists
        var newUserEntity = modelMapper.map(createUserDTO,UserEntity.class);
        newUserEntity.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        var savedUser = userRepository.save(newUserEntity);
        var userResponseDTO = modelMapper.map(savedUser, UserResponseDTO.class);
        userResponseDTO.setToken(jwtService.createJWT(savedUser.getId()));
        return userResponseDTO;
    }

    public UserResponseDTO loginUser(LoginUserDTO loginUserDTO, AuthType authType ){

        var userEntity = userRepository.findByUsername(loginUserDTO.getUsername());
        if(userEntity==null){
          throw new UserNotFoundException(loginUserDTO.getUsername());
        }
        var passMatch = passwordEncoder.matches(loginUserDTO.getPassword(),userEntity.getPassword());
        if (!passMatch){
            throw new IncorrectPasswordException();
        }
        var userResponseDTO = modelMapper.map(userEntity, UserResponseDTO.class);

        switch (authType){
            case JWT:
                userResponseDTO.setToken(jwtService.createJWT(userEntity.getId()));
                break;
            case AUTH_TOKEN:
                userResponseDTO.setToken(authTokenService.createAuthToken(userEntity).toString());
                break;
        }

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

    static enum AuthType{
        JWT,
        AUTH_TOKEN
    }
}
