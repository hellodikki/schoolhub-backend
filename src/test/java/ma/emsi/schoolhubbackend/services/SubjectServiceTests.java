package ma.emsi.schoolhubbackend.services;

import ma.emsi.schoolhubbackend.entities.Subject;
import ma.emsi.schoolhubbackend.repositories.SubjectRepository;
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
class SubjectServiceTests {

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private SubjectService subjectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveSubject() {
        Subject subject = new Subject();
        when(subjectRepository.save(any(Subject.class))).thenReturn(subject);
        Subject savedSubject = subjectService.addSubject(new Subject());
        assertNotNull(savedSubject);
        verify(subjectRepository).save(any(Subject.class));
    }

    @Test
    void testUpdateSubject() {
        Subject originalSubject = new Subject();
        originalSubject.setId(1L);
        originalSubject.setName("Math");

        Subject updatedDetails = new Subject();
        updatedDetails.setName("Advanced Math");

        when(subjectRepository.findById(1L)).thenReturn(Optional.of(originalSubject));
        when(subjectRepository.save(any(Subject.class))).thenReturn(originalSubject);

        Subject updatedSubject = subjectService.updateSubject(1L, updatedDetails);

        assertNotNull(updatedSubject);
        assertEquals("Advanced Math", updatedSubject.getName());
        verify(subjectRepository).findById(1L);
        verify(subjectRepository).save(originalSubject);
    }
}
