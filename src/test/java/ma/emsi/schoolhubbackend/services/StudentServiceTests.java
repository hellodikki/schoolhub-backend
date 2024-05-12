package ma.emsi.schoolhubbackend.services;

import ma.emsi.schoolhubbackend.entities.Student;
import ma.emsi.schoolhubbackend.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTests {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveStudent() {
        Student student = new Student();
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        Student savedStudent = studentService.addStudent(new Student());
        assertNotNull(savedStudent);
        verify(studentRepository).save(any(Student.class));
    }

    @Test
    void testFindStudentById() {
        Student student = new Student();
        student.setId(1L);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        Student foundStudent = studentService.getStudentById(1L);
        assertNotNull(foundStudent);
        assertEquals(1L, foundStudent.getId());
        verify(studentRepository).findById(1L);
    }
}
