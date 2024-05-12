package ma.emsi.schoolhubbackend.entities;

import lombok.*;
import jakarta.persistence.*;
import ma.emsi.schoolhubbackend.enums.UserGender;
import ma.emsi.schoolhubbackend.enums.UserRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "students")
public class Student extends User {

    @NotNull
    @Column(name = "studentcode", nullable = false, unique = true)
    private Long studentcode;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "lastname", nullable = false)
    private String lastname;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "parentname", nullable = false)
    private String parentname;

    @NotNull
    @Column(name = "parentphonenumber", nullable = false)
    private Long parentphonenumber;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "birthdate", nullable = false)
    private Date birthdate;

    @NotNull
    @Size(min = 5, max = 100)
    @Column(name = "address", nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    private UserGender gender;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Grade> grades;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Override
    public void setRole(UserRole role) {
        super.setRole(UserRole.STUDENT);
    }
}