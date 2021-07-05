package com.example.student.repos;

import com.example.student.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    public Optional<Student> findStudentByName(String name);
}