package ma.emsi.schoolhubbackend.services;

import ma.emsi.schoolhubbackend.entities.Grade;
import ma.emsi.schoolhubbackend.entities.Student;
import ma.emsi.schoolhubbackend.entities.Subject;
import ma.emsi.schoolhubbackend.repositories.GradeRepository;
import ma.emsi.schoolhubbackend.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private GradeRepository gradeRepository;

    public Subject addSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject updateSubject(Long subjectId, Subject subjectDetails) {
        return subjectRepository.findById(subjectId)
                .map(subject -> {
                    subject.setName(subjectDetails.getName());
                    return subjectRepository.save(subject);
                }).orElseThrow(() -> new RuntimeException("Subject not found"));
    }

    public void deleteSubject(Long subjectId) {
        subjectRepository.deleteById(subjectId);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public List<Subject> getSubjectsByStudent(Long studentId) {
        return subjectRepository.findByStudentId(studentId);
    }

    public List<Subject> findByStudentId(Long studentId) {
        return subjectRepository.findByStudentId(studentId);
    }

    public List<Subject> findByTeacherId(Long teacherId) {
        return subjectRepository.findByTeacherId(teacherId);
    }

    public List<Subject> findByGroupIdAndTeacherId(Long groupId, Long teacherId) {
        return subjectRepository.findByGroupIdAndTeacherId(groupId, teacherId);
    }
}
