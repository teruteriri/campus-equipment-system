package edu.cit.carao.estellefelicity.campusequipmentloan.service;

import edu.cit.carao.estellefelicity.campusequipmentloan.entity.StudentEntity;
import edu.cit.carao.estellefelicity.campusequipmentloan.entity.UserEntity;
import edu.cit.carao.estellefelicity.campusequipmentloan.repository.StudentRepository;
import edu.cit.carao.estellefelicity.campusequipmentloan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public StudentEntity findByStudentNo(String studentNo) {
        return studentRepository.findByStudentNo(studentNo).orElse(null);
    }

    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    public StudentEntity save(StudentEntity student) {
        if (student.getPassword() != null && !student.getPassword().isBlank()) {
            student.setPassword(passwordEncoder.encode(student.getPassword()));
        }

        StudentEntity savedStudent = studentRepository.save(student);

        UserEntity user = new UserEntity();
        user.setUsername(savedStudent.getUsername());
        user.setPassword(savedStudent.getPassword());
        user.setRole("STUDENT");

        userRepository.save(user);

        return savedStudent;
    }

    public void deleteByStudentNo(String studentNo) {
        studentRepository.findByStudentNo(studentNo).ifPresent(studentRepository::delete);
    }
}
