package com.example.student.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.aspectj.weaver.ast.Not;

import javax.persistence.*;
import java.util.List;

@Table(name = "STUDENT")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100)
    private String email;

    @Column(length = 100)
    private String imageUrl;

    @ManyToOne
    private Major major;


}