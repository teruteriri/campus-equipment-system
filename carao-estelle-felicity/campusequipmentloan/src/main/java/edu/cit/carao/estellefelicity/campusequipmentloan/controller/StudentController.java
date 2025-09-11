package edu.cit.carao.estellefelicity.campusequipmentloan.controller;

import edu.cit.carao.estellefelicity.campusequipmentloan.entity.StudentEntity;
import edu.cit.carao.estellefelicity.campusequipmentloan.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{studentNo}")
    public StudentEntity getStudent(@PathVariable String studentNo) {
        return studentService.findByStudentNo(studentNo);
    }
}