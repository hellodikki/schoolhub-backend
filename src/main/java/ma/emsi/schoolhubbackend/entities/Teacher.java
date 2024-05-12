package ma.emsi.schoolhubbackend.entities;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import ma.emsi.schoolhubbackend.enums.UserGender;
import ma.emsi.schoolhubbackend.enums.UserRole;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "teachers")
public class Teacher extends User {

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "lastname", nullable = false)
    private String lastname;

    @NotNull
    @Column(name = "cin", nullable = false)
    private String cin;

    @NotNull
    @Size(min = 10, max = 15)
    @Column(name = "phonenumber", nullable = false)
    private String phonenumber;

    @NotNull
    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Size(min = 10, max = 255)
    @Column(name = "address", nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    private UserGender gender;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Subject> subjects;

    @Override
    public void setRole(UserRole role) {
        super.setRole(UserRole.TEACHER);
    }
}