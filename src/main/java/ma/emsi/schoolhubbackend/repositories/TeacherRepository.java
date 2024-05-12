package ma.emsi.schoolhubbackend.repositories;

import ma.emsi.schoolhubbackend.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.ByteBuffer;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByUsername(String username);
}
