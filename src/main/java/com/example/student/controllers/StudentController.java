package com.example.student.controllers;

import com.example.student.respon.ResponseData;
import com.example.student.services.StudentService;
import com.example.student.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "Students")
public class StudentController {

    private final StudentService stuService;

    @Autowired
    public StudentController(StudentService stuService) {
        this.stuService = stuService;
    }

    @GetMapping
    public ResponseData<List<Student>> getAll() {
        return new ResponseData<>(0,stuService.getAllStudents(), "");
    }

    @GetMapping(path = "{studentId}")
    public ResponseData<Student> getStudentById(@PathVariable("studentId") Long studentId) {
       return new ResponseData<>(0,stuService.getStudentById(studentId),"");
    }

    @PutMapping("/edit")
    public ResponseData<Student> updateStudent(@RequestBody Student student) {
       return new ResponseData<>(0,stuService.updateStudent(student),"");
    }
    @PostMapping("/add")
    public ResponseData<Student> saveStudent(@RequestBody Student student) {
        return new ResponseData<>(0,stuService.saveStudent(student), "");
    }

    @DeleteMapping(path = "delete/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        stuService.deleteStudentById(studentId);
    }
}
