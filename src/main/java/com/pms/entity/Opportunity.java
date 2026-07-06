package com.pms.entity;

import com.pms.enums.OpportunityType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "opportunities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Opportunity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long opportunityId ;

    @ManyToOne
    @JoinColumn(
            name = "company_id" ,
            nullable = false
    )
    private Company company ;

    @OneToMany(mappedBy = "opportunity")
    private List<Application> applicationList = new ArrayList<>() ;

    @OneToMany(mappedBy = "opportunity")
    private List<Drive> drives = new ArrayList<>();

    @Column(nullable = false)
    private String title ;

    @Column(columnDefinition = "TEXT")
    private String description ;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OpportunityType type ;

    private BigDecimal salaryStipend ;

    private Double requiredCGPA ;

    @Column(nullable = false)
    private LocalDate deadline ;

    private LocalDateTime createdAt ;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now() ;
    }

}
