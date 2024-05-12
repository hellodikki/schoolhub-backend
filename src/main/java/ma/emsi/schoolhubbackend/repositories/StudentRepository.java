package ma.emsi.schoolhubbackend.repositories;

import ma.emsi.schoolhubbackend.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByUsername(String username);

    List<Student> findByGroupId(Long groupId);

    @Query("SELECT s FROM Student s JOIN s.grades g WHERE g.student.group.id = :groupId AND g.subject.id = :subjectId")
    List<Student> findByGroupIdAndSubjectId(Long groupId, Long subjectId);
}
