package edu.cit.carao.estellefelicity.campusequipmentloan.service;

import edu.cit.carao.estellefelicity.campusequipmentloan.entity.StudentEntity;
import edu.cit.carao.estellefelicity.campusequipmentloan.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentEntity findByStudentNo(String studentNo) {
        return studentRepository.findByStudentNo(studentNo).orElse(null);
    }

    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    public StudentEntity save(StudentEntity student) {
        return studentRepository.save(student);
    }

    public void deleteByStudentNo(String studentNo) {
        studentRepository.findByStudentNo(studentNo).ifPresent(studentRepository::delete);
    }
}
