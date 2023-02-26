package org.demo.blogapi.users;


import org.demo.blogapi.users.dto.CreateUserDTO;
import org.demo.blogapi.users.dto.LoginUserDTO;
import org.demo.blogapi.users.dto.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody CreateUserDTO createUserDTO) {
        var savedUser = userService.createUser(createUserDTO);
        return ResponseEntity
                .created(URI.create("/users/" + savedUser.getId()))
                .body(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> loginUser(@RequestBody LoginUserDTO loginUserDTO) {
        var loggedUser = userService.loginUser(loginUserDTO);
        return ResponseEntity
                .ok(loggedUser);
    }

    @ExceptionHandler(UserService.UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserService.UserNotFoundException e){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}