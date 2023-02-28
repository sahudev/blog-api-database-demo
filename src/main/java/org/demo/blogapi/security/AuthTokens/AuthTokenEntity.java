package org.demo.blogapi.security.AuthTokens;

import lombok.Getter;
import lombok.Setter;
import org.demo.blogapi.users.UserEntity;

import javax.persistence.*;
import java.util.UUID;

@Entity (name = "auth_tokens")
@Getter
@Setter
public class AuthTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    //@JoinColumn(name = "user_id")
    private UserEntity user;
}
