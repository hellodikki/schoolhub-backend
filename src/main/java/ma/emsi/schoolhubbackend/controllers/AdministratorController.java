package ma.emsi.schoolhubbackend.controllers;

import ma.emsi.schoolhubbackend.entities.Group;
import ma.emsi.schoolhubbackend.entities.Student;
import ma.emsi.schoolhubbackend.entities.Subject;
import ma.emsi.schoolhubbackend.entities.Teacher;
import ma.emsi.schoolhubbackend.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdministratorController {

    private final TeacherService teacherService;
    private final StudentService studentService;
    private final GroupService groupService;
    private final GradeService gradeService;
    private final SubjectService subjectService;

    @Autowired
    public AdministratorController(TeacherService teacherService, StudentService studentService, GroupService groupService, GradeService gradeService, SubjectService subjectService) {
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.groupService = groupService;
        this.gradeService = gradeService;
        this.subjectService = subjectService;
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.findAll());
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @PostMapping("/teacher")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        return ResponseEntity.ok(teacherService.save(teacher));
    }

    @PutMapping("/teacher/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher updatedTeacher) {
        return ResponseEntity.ok(teacherService.updateTeacher(id, updatedTeacher));
    }

    @DeleteMapping("/teacher/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/student")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.save(student));
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        return ResponseEntity.ok(studentService.updateStudent(id, updatedStudent));
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/group")
    public ResponseEntity<Group> createGroup(@RequestBody Group group) {
        return ResponseEntity.ok(groupService.save(group));
    }

    @PutMapping("/group/{id}")
    public ResponseEntity<Group> updateGroup(@PathVariable Long id, @RequestBody Group updatedGroup) {
        return ResponseEntity.ok(groupService.updateGroup(id, updatedGroup));
    }

    @DeleteMapping("/group/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/subjects")
    public ResponseEntity<List<Subject>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @PostMapping("/subject")
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        return ResponseEntity.ok(subjectService.addSubject(subject));
    }

    @PutMapping("/subject/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable Long id, @RequestBody Subject updatedSubject) {
        return ResponseEntity.ok(subjectService.updateSubject(id, updatedSubject));
    }

    @DeleteMapping("/subject/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/transcript/student/{studentId}")
    public ResponseEntity<String> generateTranscript(@PathVariable Long studentId) {
        return studentService.findById(studentId)
                .map(student -> {
                    StringBuilder transcript = new StringBuilder("Transcript for ")
                            .append(student.getFirstname()).append(" ").append(student.getLastname()).append("\n");

                    student.getGroup().getSubjects().forEach(subject -> {
                        gradeService.findAll().stream()
                                .filter(g -> g.getSubject().equals(subject) && g.getStudent().equals(student))
                                .findFirst()
                                .ifPresent(grade -> transcript.append(subject.getName()).append(": ").append(grade.getGradevalue()).append("\n"));
                    });

                    return ResponseEntity.ok(transcript.toString());
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/certificate/student/{studentId}")
    public ResponseEntity<String> generateCertificate(@PathVariable Long studentId) {
        return studentService.findById(studentId)
                .map(student -> {
                    String certificate = "Education Certificate\n" +
                            "This is to certify that " + student.getFirstname() + " " + student.getLastname() +
                            " is a student in the " + student.getGroup().getYear() + " of " + student.getGroup().getSector() + ".\n";

                    return ResponseEntity.ok(certificate);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
