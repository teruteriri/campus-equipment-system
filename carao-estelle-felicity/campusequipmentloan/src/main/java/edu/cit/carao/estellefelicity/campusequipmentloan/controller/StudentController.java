package edu.cit.carao.estellefelicity.campusequipmentloan.controller;

import edu.cit.carao.estellefelicity.campusequipmentloan.entity.StudentEntity;
import edu.cit.carao.estellefelicity.campusequipmentloan.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{studentNo}")
    public StudentEntity getStudent(@PathVariable String studentNo) {
        return studentService.findByStudentNo(studentNo);
    }

    @GetMapping("/all")
    public List<StudentEntity> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/register")
    public StudentEntity registerStudent(@RequestBody StudentEntity student) {
        return studentService.save(student);
    }

    @DeleteMapping("/delete/{studentNo}")
    public void deleteStudent(@PathVariable String studentNo) {
        studentService.deleteByStudentNo(studentNo);
    }
}
