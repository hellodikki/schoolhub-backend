package ma.emsi.schoolhubbackend.entities;

import jakarta.persistence.*;
import lombok.*;
import ma.emsi.schoolhubbackend.enums.UserRole;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name="administrators")
public class Administrator extends User{

    public Administrator() {
        super();
        this.setRole(UserRole.ADMINISTRATOR);
    }

}
