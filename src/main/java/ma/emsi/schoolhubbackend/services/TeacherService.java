package ma.emsi.schoolhubbackend.services;

import ma.emsi.schoolhubbackend.entities.Teacher;
import ma.emsi.schoolhubbackend.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Long teacherId, Teacher teacherDetails) {
        return teacherRepository.findById(teacherId)
                .map(teacher -> {
                    teacher.setFirstname(teacherDetails.getFirstname());
                    teacher.setLastname(teacherDetails.getLastname());
                    teacher.setCin(teacherDetails.getCin());
                    teacher.setPhonenumber(teacherDetails.getPhonenumber());
                    teacher.setEmail(teacherDetails.getEmail());
                    teacher.setAddress(teacherDetails.getAddress());
                    teacher.setGender(teacherDetails.getGender());
                    return teacherRepository.save(teacher);
                }).orElseThrow(() -> new RuntimeException("Teacher not found"));
    }

    public void deleteTeacher(Long teacherId) {
        teacherRepository.deleteById(teacherId);
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }
}

