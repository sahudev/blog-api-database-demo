package org.demo.blogapi.users;

import org.demo.blogapi.security.AuthTokens.AuthTokenService;
import org.demo.blogapi.users.dto.CreateUserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.demo.blogapi.security.jwt.JWTService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;
    private UserService userService;


    private UserService getUserService(){
        if(userService==null){
            var modelMapper = new ModelMapper();
            var passwordEncoder = new BCryptPasswordEncoder();
            var jwtService = new JWTService();
           // var authTokenService = new AuthTokenService();
          //  userService = new UserService(userRepository,modelMapper,passwordEncoder, jwtService);
        }
        return userService;
    }

    public void testCreateUser(){
        var newUserDTO = new CreateUserDTO();
        newUserDTO.setEmail("dev@sahu.com");
        newUserDTO.setUsername("sahudevTest");
        newUserDTO.setEmail("password112");
        var savedUser = getUserService().createUser(newUserDTO);
        assertNotNull(savedUser);
    }

}
