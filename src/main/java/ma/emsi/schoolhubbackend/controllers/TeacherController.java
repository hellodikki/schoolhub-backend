package ma.emsi.schoolhubbackend.controllers;

import ma.emsi.schoolhubbackend.entities.Grade;
import ma.emsi.schoolhubbackend.entities.Student;
import ma.emsi.schoolhubbackend.entities.Subject;
import ma.emsi.schoolhubbackend.services.GradeService;
import ma.emsi.schoolhubbackend.services.StudentService;
import ma.emsi.schoolhubbackend.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private final SubjectService subjectService;
    private final StudentService studentService;
    private final GradeService gradeService;

    @Autowired
    public TeacherController(SubjectService subjectService, StudentService studentService, GradeService gradeService) {
        this.subjectService = subjectService;
        this.studentService = studentService;
        this.gradeService = gradeService;
    }

    @GetMapping("/subjects/{teacherId}")
    public ResponseEntity<List<Subject>> getSubjectsByTeacher(@PathVariable Long teacherId) {
        List<Subject> subjects = subjectService.findByTeacherId(teacherId);
        if (subjects.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/subjects/{groupId}/{teacherId}")
    public ResponseEntity<List<Subject>> getByGroupIdAndTeacherId(@PathVariable Long groupId, @PathVariable Long teacherId) {
        List<Subject> subjects = subjectService.findByGroupIdAndTeacherId(groupId, teacherId);
        if (subjects.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(subjects);
    }

    @PostMapping("/grade")
    public ResponseEntity<Grade> addGrade(@RequestBody Grade newGrade) {
        Grade savedGrade = gradeService.save(newGrade);
        return ResponseEntity.ok(savedGrade);
    }

    @PutMapping("/grade/{gradeId}")
    public ResponseEntity<Grade> updateGrade(@PathVariable Long gradeId, @RequestBody Grade updatedGrade) {
        Grade grade = gradeService.updateGrade(gradeId, updatedGrade);
        if (grade == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(grade);
    }

    @GetMapping("/students/{groupId}/{subjectId}")
    public ResponseEntity<List<Student>> getStudentByGroupIdAndSubjectId(@PathVariable Long subjectId, @PathVariable Long groupId) {
        List<Student> students = studentService.findByGroupIdAndSubjectId(groupId, subjectId);
        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping("/students/{studentId}/subjects")
    public ResponseEntity<List<Subject>> getSubjectsByStudent(@PathVariable Long studentId) {
        List<Subject> subjects = subjectService.findByStudentId(studentId);
        if (subjects.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/grades/{groupId}/{subjectId}")
    public ResponseEntity<List<Grade>> getGradesByGroupIdAndSubjectId(@PathVariable Long groupId, @PathVariable Long subjectId) {
        List<Grade> grades = gradeService.findByGroupIdAndSubjectId(groupId, subjectId);
        if (grades.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(grades);
    }
}
