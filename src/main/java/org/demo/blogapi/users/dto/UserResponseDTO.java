package org.demo.blogapi.users.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDTO {
    String id;
    String email;
    String username;
    String bio;
    String image;
}
