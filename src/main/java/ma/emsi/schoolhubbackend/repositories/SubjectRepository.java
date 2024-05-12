package ma.emsi.schoolhubbackend.repositories;

import ma.emsi.schoolhubbackend.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    @Query("SELECT g.subject, g.gradevalue FROM Grade g WHERE g.student.id = :studentId")
    List<Subject> findByStudentId(@Param("studentId") Long studentId);

    List<Subject> findByTeacherId(Long teacherId);

    @Query("SELECT g.subject FROM Grade g WHERE g.student.group.id = :groupId AND g.subject.teacher.id = :teacherId")
    List<Subject> findByGroupIdAndTeacherId(Long groupId, Long teacherId);
}
