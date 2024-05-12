package ma.emsi.schoolhubbackend.services;

import ma.emsi.schoolhubbackend.entities.Teacher;
import ma.emsi.schoolhubbackend.repositories.TeacherRepository;
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
class TeacherServiceTests {

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private TeacherService teacherService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTeacher() {
        Teacher teacher = new Teacher();
        when(teacherRepository.save(any(Teacher.class))).thenReturn(teacher);
        Teacher savedTeacher = teacherService.addTeacher(new Teacher());
        assertNotNull(savedTeacher);
        verify(teacherRepository).save(any(Teacher.class));
    }

    @Test
    void testUpdateTeacher() {
        Teacher originalTeacher = new Teacher();
        originalTeacher.setId(1L);
        originalTeacher.setFirstname("John");

        Teacher updatedDetails = new Teacher();
        updatedDetails.setFirstname("Jane");

        when(teacherRepository.findById(1L)).thenReturn(Optional.of(originalTeacher));
        when(teacherRepository.save(any(Teacher.class))).thenReturn(originalTeacher);

        Teacher updatedTeacher = teacherService.updateTeacher(1L, updatedDetails);

        assertNotNull(updatedTeacher);
        assertEquals("Jane", updatedTeacher.getFirstname());
        verify(teacherRepository).findById(1L);
        verify(teacherRepository).save(originalTeacher);
    }

    @Test
    void testDeleteTeacher() {
        doNothing().when(teacherRepository).deleteById(1L);
        teacherService.deleteTeacher(1L);
        verify(teacherRepository).deleteById(1L);
    }
}
