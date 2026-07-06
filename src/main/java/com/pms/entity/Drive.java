package com.pms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "drives")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Drive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driveId ;

    @ManyToOne
    @JoinColumn(
            name = "opportunity_id",
            nullable = false
    )
    private Opportunity opportunity;

    @ManyToOne
    @JoinColumn(
            name = "company_id" ,
            nullable = false
    )
    private Company company ;

    @ManyToOne
    @JoinColumn(
            name = "coordinator_id" ,
            nullable = false
    )
    private Coordinator coordinator ;

    @Column(nullable = false)
    private String title ;

    @Column(nullable = false)
    private LocalDate driveDate ;

    private String venue ;
}
