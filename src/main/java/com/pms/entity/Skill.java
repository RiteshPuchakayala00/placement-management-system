package com.pms.entity;

import jakarta.persistence.*;
import lombok.* ;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "skills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillId ;

    @Column(nullable = false, unique = true)
    private String name ;

    @ManyToMany(mappedBy = "skills")
    private Set<Student> students = new HashSet<>();
}
