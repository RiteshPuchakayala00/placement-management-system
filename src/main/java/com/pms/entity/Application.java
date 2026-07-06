package com.pms.entity;

import com.pms.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "applications" ,
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "student_id" ,
                                "opportunity_id"
                        }
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId ;

    @ManyToOne
    @JoinColumn(
            name = "student_id" ,
            nullable = false
    )
    private Student student ;

    @ManyToOne
    @JoinColumn(
            name = "opportunity_id" ,
            nullable = false
    )
    private Opportunity opportunity ;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status ;

    private LocalDateTime appliedAt ;

    @PrePersist
    public void prePersist() {
        appliedAt = LocalDateTime.now() ;

        if(status == null){
            status = ApplicationStatus.APPLIED;
        }
    }


}
