package ma.emsi.schoolhubbackend.services;

import ma.emsi.schoolhubbackend.entities.Grade;
import ma.emsi.schoolhubbackend.repositories.GradeRepository;
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
class GradeServiceTests {

    @Mock
    private GradeRepository gradeRepository;

    @InjectMocks
    private GradeService gradeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddGrade() {
        Grade grade = new Grade();
        when(gradeRepository.save(any(Grade.class))).thenReturn(grade);
        Grade savedGrade = gradeService.addGrade(new Grade());
        assertNotNull(savedGrade);
        verify(gradeRepository).save(any(Grade.class));
    }

    @Test
    void testUpdateGrade() {
        Grade originalGrade = new Grade();
        originalGrade.setId(1L);
        originalGrade.setGradevalue(90.0f);

        Grade updatedDetails = new Grade();
        updatedDetails.setGradevalue(95.0f);

        when(gradeRepository.findById(1L)).thenReturn(Optional.of(originalGrade));
        when(gradeRepository.save(any(Grade.class))).thenReturn(originalGrade);

        Grade updatedGrade = gradeService.updateGrade(1L, updatedDetails);

        assertNotNull(updatedGrade);
        assertEquals(95.0f, updatedGrade.getGradevalue());
        verify(gradeRepository).findById(1L);
        verify(gradeRepository).save(originalGrade);
    }

    @Test
    void testDeleteGrade() {
        doNothing().when(gradeRepository).deleteById(1L);
        gradeService.deleteGrade(1L);
        verify(gradeRepository).deleteById(1L);
    }
}
