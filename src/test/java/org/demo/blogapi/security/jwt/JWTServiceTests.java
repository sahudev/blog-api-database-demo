package org.demo.blogapi.security.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class JWTServiceTests {

    private JWTService jwtService = new JWTService();

    @Test
    void canCreateJWTFromUserId(){
        var userId = 1122;
        var jwt = jwtService.createJWT(userId,new Date(1677438053), new Date(1678042853));
        assertEquals("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTIyIiwiZXhwIjoxNjc4MDQyLCJpYXQiOjE2Nzc0Mzh9.s841mgHASlw6yobS6PWQMdDrU5RMnwGt_bNIfGrpvYU",jwt);
    }

    @Test
    void canVerifyJWT(){
        var jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTIyIiwiZXhwIjoxNjc4MDQyODUzLCJpYXQiOjE2Nzc0MzgwNTN9.ITEZidF7x9PcqoikODEdNB-ZJgREfpWGQUW_Wc5UOMk";
        var userId = jwtService.getUserIdFromJWT(jwt);
        assertEquals(1122,userId);
    }

}
