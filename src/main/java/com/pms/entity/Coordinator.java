package com.pms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "coordinators")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Coordinator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coordinatorId ;

    @OneToOne
    @JoinColumn(
        name = "user_id",
        nullable = false,
        unique = true
    )
    private User user ;

    @OneToMany(mappedBy = "coordinator")
    private List<Drive> drives = new ArrayList<>();

    @Column(nullable = false)
    private String designation ;

}
