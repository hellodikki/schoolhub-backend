package ma.emsi.schoolhubbackend.services;

import ma.emsi.schoolhubbackend.entities.Student;
import ma.emsi.schoolhubbackend.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long studentId, Student studentDetails) {
        return studentRepository.findById(studentId)
                .map(student -> {
                    student.setFirstname(studentDetails.getFirstname());
                    student.setLastname(studentDetails.getLastname());
                    student.setStudentcode(studentDetails.getStudentcode());
                    student.setParentname(studentDetails.getParentname());
                    student.setParentphonenumber(studentDetails.getParentphonenumber());
                    student.setBirthdate(studentDetails.getBirthdate());
                    student.setAddress(studentDetails.getAddress());
                    student.setGender(studentDetails.getGender());
                    return studentRepository.save(student);
                }).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public List<Student> getStudentsByGroup(Long groupId) {
        return studentRepository.findByGroupId(groupId);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> findById(Long studentId) {
        return studentRepository.findById(studentId);
    }

    public List<Student> findByGroupIdAndSubjectId(Long groupId, Long subjectId) {
        return studentRepository.findByGroupIdAndSubjectId(groupId, subjectId);
    }

}
