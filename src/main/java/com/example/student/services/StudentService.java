package com.example.student.services;

import com.example.student.repos.StudentRepository;
import com.example.student.exeption.UserExitedExeption;
import com.example.student.exeption.UserNotFoundExeption;
import com.example.student.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository stuRepo;

    @Autowired
    public StudentService(StudentRepository stuRepo) {
        this.stuRepo = stuRepo;
    }

    public List<Student> getAllStudents() {
        return stuRepo.findAll();
    }

    public Student getStudentById(Long id) {
        Optional<Student> stu = stuRepo.findById(id);

        if (stu.isPresent()) {
            return stu.get();
        } else
            throw new UserNotFoundExeption("No Found with id = " + id);

    }

    public Student saveStudent(Student stu) {
        Optional<Student> student = stuRepo.findStudentByName(stu.getName());
        if (!student.isPresent())
            return stuRepo.save(stu);
        else
            throw new UserExitedExeption("Name already exits");

    }


    public Student updateStudent(Student student) {
        Student newStudent = stuRepo.findById(student.getId()).orElseThrow(()
                -> new UserNotFoundExeption("Can not find student"));
        return this.stuRepo.save(student);

    }

    public void deleteStudentById(Long id) {
        Student student = stuRepo.findById(id).orElseThrow(()
                -> new UserNotFoundExeption("Not Found with id = " + id));

        stuRepo.deleteById(id);
    }


}
