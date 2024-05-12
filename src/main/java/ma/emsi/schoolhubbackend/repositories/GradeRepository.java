package ma.emsi.schoolhubbackend.repositories;

import ma.emsi.schoolhubbackend.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudentId(Long studentId);

    @Query("SELECT g FROM Grade g WHERE g.student.group.id = ?1 AND g.subject.id = ?2")
    List<Grade> findByGroupIdAndSubjectId(Long groupId, Long subjectId);
}

