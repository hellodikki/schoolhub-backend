package ma.emsi.schoolhubbackend.services;

import ma.emsi.schoolhubbackend.entities.Grade;
import ma.emsi.schoolhubbackend.repositories.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public Grade addGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    public Grade updateGrade(Long gradeId, Grade gradeDetails) {
        return gradeRepository.findById(gradeId)
                .map(grade -> {
                    grade.setGradevalue(gradeDetails.getGradevalue());
                    grade.setGradedate(gradeDetails.getGradedate());
                    return gradeRepository.save(grade);
                }).orElseThrow(() -> new RuntimeException("Grade not found"));
    }

    public void deleteGrade(Long gradeId) {
        gradeRepository.deleteById(gradeId);
    }

    public List<Grade> getGradesByStudent(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }


    public List<Grade> findAll() {
        return gradeRepository.findAll();
    }

    public List<Grade> findByStudentId(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    public Grade save(Grade newGrade) {
        return gradeRepository.save(newGrade);
    }

    public List<Grade> findByGroupIdAndSubjectId(Long groupId, Long subjectId) {
        return gradeRepository.findByGroupIdAndSubjectId(groupId, subjectId);
    }
}

