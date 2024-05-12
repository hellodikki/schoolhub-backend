package ma.emsi.schoolhubbackend.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ADMINISTRATOR,
    TEACHER,
    STUDENT;

    @Override
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}