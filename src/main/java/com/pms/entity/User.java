package com.pms.entity;

import com.pms.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId ;

    @OneToOne(mappedBy = "user")
    private Student student;

    @OneToOne(mappedBy = "user")
    private Company company ;

    @OneToOne(mappedBy = "user")
    private Coordinator coordinator;

    @Column(nullable = false , length = 100)
    private String name ;

    @Column(nullable = false , unique = true)
    private String email ;

    @Column(nullable = false)
    private String password ;

    @Enumerated(EnumType.STRING)
    private Role role ;

    @Column(nullable = false)
    private LocalDateTime createdAt ;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now() ;
    }

}
