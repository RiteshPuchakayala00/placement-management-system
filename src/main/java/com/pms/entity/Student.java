package com.pms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId ;

    @OneToOne
    @JoinColumn(
            name = "user_id" ,
            nullable = false ,
            unique = true
    )
    private User user ;

    @ManyToMany
    @JoinTable(
            name = "student_skills" ,
            joinColumns = @JoinColumn(name = "student_id") ,
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills = new HashSet<>();

    @OneToMany(mappedBy = "student")
    private List<Application> applications = new ArrayList<>() ;

    @Column(nullable = false , unique = false)
    private String rollNo ;

    @Column(nullable = false)
    private String department ;

    @Column(nullable = false)
    private Double cgpa ;

    @Column(nullable = false)
    private String phone ;

    @Column(nullable = false)
    private Integer graduationYear ;

    private String resumeUrl ;

}
