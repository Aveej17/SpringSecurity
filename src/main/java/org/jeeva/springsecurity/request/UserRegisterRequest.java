package org.jeeva.springsecurity.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jeeva.springsecurity.model.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {

    private String username;
    private String password;

    public User toUser(){
        return User
                .builder()
                .username(this.username)
                .password(this.password)
                .build();
    }
}
