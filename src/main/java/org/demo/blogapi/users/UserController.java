package org.demo.blogapi.users;


import org.demo.blogapi.users.dto.CreateUserDTO;
import org.demo.blogapi.users.dto.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody CreateUserDTO createUserDTO){
        var savedUser = userService.createUser(createUserDTO);
        return ResponseEntity
                .created(URI.create("/users/"+savedUser.getId()))
                .body(savedUser);
    }

}
