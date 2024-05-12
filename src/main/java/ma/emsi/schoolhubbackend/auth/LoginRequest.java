package ma.emsi.schoolhubbackend.auth;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    private String username;

    String password;

}
