package com.example.student.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "MAJOR")
@Entity
@Data
public class Major {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @JsonIgnore
    @OneToMany
    private List<Student> students;

    @Column(nullable = false)
    private String majorCode;
}