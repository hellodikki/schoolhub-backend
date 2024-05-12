package ma.emsi.schoolhubbackend.controllers;

import ma.emsi.schoolhubbackend.entities.Grade;
import ma.emsi.schoolhubbackend.entities.Subject;
import ma.emsi.schoolhubbackend.services.GradeService;
import ma.emsi.schoolhubbackend.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final SubjectService subjectService;
    private final GradeService gradeService;

    @Autowired
    public StudentController(SubjectService subjectService, GradeService gradeService) {
        this.subjectService = subjectService;
        this.gradeService = gradeService;
    }

    @GetMapping("/subjects/{studentId}")
    public ResponseEntity<List<Subject>> getSubjectsByStudent(@PathVariable Long studentId) {
        List<Subject> subjects = subjectService.findByStudentId(studentId);
        if (subjects.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/grades/{studentId}")
    public ResponseEntity<List<Grade>> getGradesByStudent(@PathVariable Long studentId) {
        List<Grade> grades = gradeService.findByStudentId(studentId);
        if (grades.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(grades);
    }
}
